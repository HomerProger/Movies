package com.example.movies.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.databinding.UpcomingFragmentBinding
import com.example.movies.model.ADULT
import com.example.movies.model.MovieDTO
import com.example.movies.view.OnItemViewClickListener
import com.example.movies.viewmodel.AppState
import com.example.movies.viewmodel.UpcomingViewModel


class UpcomingFragment : Fragment() {
    private var upcomingAdapter: UpcomingAdapter = UpcomingAdapter(object :
        OnItemViewClickListener {
        override fun onItemViewClick(movieDTO: MovieDTO) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        DetailsFragment.newInstance(Bundle().apply {
                            putParcelable(
                                DetailsFragment.KEY_MOVIE,
                                movieDTO
                            )
                        })
                    )
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })
    val viewModel: UpcomingViewModel by lazy { ViewModelProvider(this).get(UpcomingViewModel::class.java) }
    var _binding: UpcomingFragmentBinding? = null
    val binding: UpcomingFragmentBinding
        get() :UpcomingFragmentBinding = _binding!!


    companion object {
        fun newInstance() = UpcomingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UpcomingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getUpcomingMovieListFromRemoteSource()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        upcomingAdapter.removeListener()
    }

    private fun renderData(appState: AppState) {
            when (appState) {
                is AppState.Error -> TODO()
                is AppState.Success -> {
                    setData(appState)
                }
                AppState.Loading -> {
                }
            }
    }

    private fun setData(appState: AppState.Success) {
            upcomingAdapter.setMovie(checkAdultContent(appState.dataMovies))
            with(binding){
                recyclerViewUpcoming.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewUpcoming.adapter = upcomingAdapter
            }
    }

    private fun checkAdultContent(dataMovies: MutableList<MovieDTO>): MutableList<MovieDTO> {
        val movieList: MutableList<MovieDTO> = mutableListOf()
        val sharedPref = context?.getSharedPreferences(ADULT, Context.MODE_PRIVATE)
        val adultContent: Boolean = sharedPref?.getBoolean(ADULT, true)!!

        for (i in dataMovies) {
            if (adultContent) {
                movieList.add(i)
            } else {
                if (i.adult) continue
                else movieList.add(i)
            }
        }
        return movieList
    }

}