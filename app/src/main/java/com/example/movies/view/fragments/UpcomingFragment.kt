package com.example.movies.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.databinding.UpcomingFragmentBinding
import com.example.movies.model.Movie
import com.example.movies.model.MovieDTO
import com.example.movies.model.MovieListDTO
import com.example.movies.view.MovieLoader
import com.example.movies.view.MovieLoaderListener
import com.example.movies.view.OnItemViewClickListener
import com.example.movies.view.adapters.UpcomingAdapter
import com.example.movies.view.showSnackBar
import com.example.movies.viewmodel.AppState
import com.example.movies.viewmodel.UpcomingViewModel
import kotlinx.android.synthetic.main.activity_main.*


class UpcomingFragment : Fragment(), MovieLoaderListener {
    private val THIS_FRAGMENT = "UPCOMING"
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getNewMovies()
        MovieLoader(this, THIS_FRAGMENT).loadMovie()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        upcomingAdapter.removeListener()
    }

    private fun renderData(appState: AppState) {
           /*
            when (appState) {
                is AppState.Error -> TODO()
                is AppState.Success -> {
                    setData(appState)
                    binding.root.showSnackBar(R.string.snackbar_text)
                }
                AppState.Loading -> {
                }
            }
            */
    }

    private fun setData(appState: AppState.Success) {
        /*
            UpcomingAdapter.setMovie(appState.dataMovies)
            with(binding){
                recyclerViewUpcoming.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewUpcoming.adapter = UpcomingAdapter
            }
         */
    }

    override fun onLoaded(movieListDTO: MovieListDTO) {
        val movieList: MutableList<MovieDTO> = mutableListOf()
        for (i in movieListDTO.results) {
            movieList.add(i)
        }

        upcomingAdapter.setMovie(movieList)
        with(binding) {
            recyclerViewUpcoming.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewUpcoming.adapter = upcomingAdapter
        }
    }

    override fun onFailed(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}