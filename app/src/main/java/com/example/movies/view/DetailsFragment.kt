package com.example.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.movies.databinding.DetailsFragmentBinding
import com.example.movies.model.Movie

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
        get() :DetailsFragmentBinding {
            return _binding!!
        }

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
        val movie = arguments?.getParcelable(KEY_MOVIE) as? Movie
        if (movie != null)
            setData(movie)

    }

    private fun setData(movie: Movie) {
        binding.detailsNameRus.text = "${movie.movieNameRus} (${movie.yearOfMovie})"
        binding.detailsNameEng.text = movie.movieNameEng
        binding.jenre.text = movie.genres
        binding.duration.text = "${movie.duration} мин"
        binding.rating.text = "${movie.rating} (${movie.voteCount})"
        binding.budjet.text = movie.budget
        binding.dateOfPremiereRus.text = "Премьера в РФ ${movie.dateOfPremiereRus}"
        binding.description.text = movie.description
        binding.detailsPoster.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                movie.poster
            )
        )


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}