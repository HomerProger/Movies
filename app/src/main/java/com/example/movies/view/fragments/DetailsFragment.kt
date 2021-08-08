package com.example.movies.view.fragments

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
        arguments?.getParcelable<Movie>(KEY_MOVIE)?.apply {
            with(binding) {
                detailsNameRus.text = "${movieNameRus} (${yearOfMovie})"
                detailsNameEng.text = this@apply.movieNameEng
                jenre.text = genres
                duration.text = "${this@apply.duration} мин"
                rating.text = "${this@apply.rating} (${this@apply.voteCount})"
                budjet.text = budget
                dateOfPremiereRus.text = "Премьера в РФ ${this@apply.dateOfPremiereRus}"
                description.text = this@apply.description
                detailsPoster.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        poster
                    )
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}