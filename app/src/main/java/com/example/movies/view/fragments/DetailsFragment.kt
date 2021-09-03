package com.example.movies.view.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movies.databinding.DetailsFragmentBinding
import com.example.movies.model.MovieDTO
import com.example.movies.model.MovieDetailsDTO
import com.example.movies.room.HistoryEntity
import com.example.movies.viewmodel.AppState
import com.example.movies.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target

class DetailsFragment : Fragment() {

    companion object {
        val KEY_MOVIE: String = "key_movie"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    val viewModel: DetailsViewModel by lazy { ViewModelProvider(this).get(DetailsViewModel::class.java) }
    var _binding: DetailsFragmentBinding? = null
    val binding: DetailsFragmentBinding
        get() :DetailsFragmentBinding = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        arguments?.getParcelable<MovieDTO>(KEY_MOVIE)?.id?.let {
            viewModel.getMovieDetailsFromRemoteSource(it.toInt())
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessDetails -> setData(appState)
        }
    }

    private fun setData(appState: AppState.SuccessDetails) {
        val movieDetails: MovieDetailsDTO = appState.dataDetails
        val length = 4
        val makeEntryDB = MakeEntryDB(this)
        makeEntryDB.execute(movieDetails)
        with(binding) {
            detailsNameRus.text =
                "${movieDetails.title} (${movieDetails.release_date.subSequence(0, length)})"
            detailsNameEng.text = movieDetails.original_title
            jenre.text = getGenres(movieDetails)
            duration.text = "${movieDetails.runtime} мин"
            budjet.text = "Бюджет $${movieDetails.budget}"
            rating.text = "Рейтинг ${movieDetails.vote_average} (${movieDetails.vote_count})"
            dateOfPremiereRus.text = "Премьера  ${movieDetails.release_date}"
            description.text = movieDetails.overview
        }
        Picasso.get()
            .load("https://image.tmdb.org/t/p/original${movieDetails.poster_path}")
            .into(binding.detailsPoster)
        Picasso.get().load("https://image.tmdb.org/t/p/original${movieDetails.poster_path}")
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: LoadedFrom) {
                    binding.root.background = BitmapDrawable(bitmap)
                    binding.root.background.alpha = 20
                }

                override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {}
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            })
    }

    private fun getGenres(movieDetails: MovieDetailsDTO): String {
        var genres = ""
        for (i in movieDetails.genres) {
            genres = "${genres}${i.name}, "
        }
        return genres.substring(0, genres.length - 2)
    }

}

internal class MakeEntryDB(detailsFragment: DetailsFragment) :
    AsyncTask<MovieDetailsDTO?, Unit?, Unit?>() {
    val viewModel: DetailsViewModel =
        ViewModelProvider(detailsFragment).get(DetailsViewModel::class.java)

    override fun doInBackground(vararg movieDetails: MovieDetailsDTO?) {
        movieDetails[0]?.let { viewModel.saveMovieToDb(it) }
    }
}