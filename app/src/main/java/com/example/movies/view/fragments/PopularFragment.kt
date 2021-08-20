package com.example.movies.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.databinding.PopularFragmentBinding
import com.example.movies.model.MovieDTO
import com.example.movies.model.MovieListDTO
import com.example.movies.view.MovieLoader
import com.example.movies.view.MovieLoaderListener
import com.example.movies.view.OnItemViewClickListener
import com.example.movies.viewmodel.AppState
import com.example.movies.viewmodel.PopularViewModel


class PopularFragment : Fragment(), MovieLoaderListener {
    private val THIS_FRAGMENT = "POPULAR"
    private var popularAdapter: PopularAdapter = PopularAdapter(object :
        OnItemViewClickListener {
        override fun onItemViewClick(movieDTO: MovieDTO) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(
                        R.id.fragment_container, DetailsFragment.newInstance(Bundle()
                            .apply { putParcelable(DetailsFragment.KEY_MOVIE, movieDTO) })
                    )
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    val viewModel: PopularViewModel by lazy { ViewModelProvider(this).get(PopularViewModel::class.java) }
    var _binding: PopularFragmentBinding? = null
    val binding: PopularFragmentBinding
        get() :PopularFragmentBinding = _binding!!


    companion object {
        fun newInstance() = PopularFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PopularFragmentBinding.inflate(inflater, container, false)
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
        popularAdapter.removeListener()
    }

    private fun renderData(appState: AppState) {
        /* when (appState) {
             is AppState.Error -> TODO()
             is AppState.Success -> {
                 setData(appState)
             }
             AppState.Loading -> {
             }
         }
         */
    }

    private fun setData(appState: AppState.Success) {
        /*   popularAdapter.setMovie(appState.dataMovies)
           with(binding) {
               recyclerViewPopular.layoutManager =
                   LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
               recyclerViewPopular.adapter = popularAdapter
           }
                 */
    }

    override fun onLoaded(movieListDTO: MovieListDTO) {
        val movieList: MutableList<MovieDTO> = mutableListOf()
        for (i in movieListDTO.results) {
            movieList.add(i)
        }

        popularAdapter.setMovie(movieList)
        with(binding) {
            recyclerViewPopular.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewPopular.adapter = popularAdapter
        }
    }

    override fun onFailed(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}