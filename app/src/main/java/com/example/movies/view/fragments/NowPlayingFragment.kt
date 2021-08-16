package com.example.movies.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.databinding.NowPlayingFragmentBinding
import com.example.movies.model.Movie
import com.example.movies.model.MovieDTO
import com.example.movies.model.MovieListDTO
import com.example.movies.view.MovieLoader
import com.example.movies.view.MovieLoaderListener
import com.example.movies.view.adapters.NowPlayingAdapter
import com.example.movies.view.OnItemViewClickListener
import com.example.movies.view.showSnackBar
import com.example.movies.viewmodel.AppState
import com.example.movies.viewmodel.NowPlayingViewModel


class NowPlayingFragment : Fragment(), MovieLoaderListener {
    private val THIS_FRAGMENT = "NOW"
    private var nowPlayingAdapter: NowPlayingAdapter = NowPlayingAdapter(object :
        OnItemViewClickListener {
        override fun onItemViewClick(movieDTO: MovieDTO) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.fragment_container, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.KEY_MOVIE, movieDTO)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })
    val viewModel: NowPlayingViewModel by lazy { ViewModelProvider(this).get(NowPlayingViewModel::class.java) }
    var _binding: NowPlayingFragmentBinding? = null
    val binding: NowPlayingFragmentBinding
        get() :NowPlayingFragmentBinding = _binding!!


    companion object {
        fun newInstance() = NowPlayingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NowPlayingFragmentBinding.inflate(inflater, container, false)
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
        nowPlayingAdapter.removeListener()
    }

    private fun renderData(appState: AppState) {
       /* when (appState) {
            is AppState.Error -> TODO()
            is AppState.Success -> {
                setData(appState)
                binding.root.showSnackBar("Данные получены NowPlayingFragment")
            }
            AppState.Loading -> {
            }
        }
        */
    }

    private fun setData(appState: AppState.Success) {
       /* nowPlayingAdapter.setMovie(appState.dataMovies)
        with(binding) {
            recyclerViewNow.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewNow.adapter = nowPlayingAdapter
        }
        */
    }

    override fun onLoaded(movieListDTO: MovieListDTO) {
        val movieList: MutableList<MovieDTO> = mutableListOf()
        for (i in movieListDTO.results) {
            movieList.add(i)
        }

        nowPlayingAdapter.setMovie(movieList)
        with(binding) {
            recyclerViewNow.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewNow.adapter = nowPlayingAdapter
        }
    }

    override fun onFailed(throwable: Throwable) {
        TODO("Not yet implemented")
    }

}