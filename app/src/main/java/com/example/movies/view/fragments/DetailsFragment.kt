package com.example.movies.view.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movies.databinding.DetailsFragmentBinding
import com.example.movies.model.MovieDTO
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getParcelable<MovieDTO>(KEY_MOVIE)?.apply {
            val length = 4
            with(binding) {
                detailsNameRus.text = "${title} (${release_date.subSequence(0, length)})"
                detailsNameEng.text = original_title
                rating.text = "Рейтинг ${vote_average} (${vote_count})"
                dateOfPremiereRus.text = "Премьера в РФ ${release_date}"
                description.text = overview
//                TODO("Получить остальные данные и заполнить все поля в DetailsFragment")
            }
            Picasso.get()
                .load("https://image.tmdb.org/t/p/original${poster_path}")
                .into(binding.detailsPoster)
            Picasso.get().load("https://image.tmdb.org/t/p/original${poster_path}")
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap, from: LoadedFrom) {
                        binding.root.background = BitmapDrawable(bitmap)
                        binding.root.background.alpha = 20
                    }

                    override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {}
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
                })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(KEY_MOVIE).toString()

    }
}