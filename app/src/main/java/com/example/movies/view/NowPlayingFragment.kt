package com.example.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.databinding.NowPlayingFragmentBinding
import com.example.movies.model.Movie
import com.example.movies.viewmodel.AppState
import com.example.movies.viewmodel.NowPlayingViewModel


class NowPlayingFragment : Fragment() {
    private var nowPlayingAdapter: NowPlayingAdapter = NowPlayingAdapter(object :
        OnItemViewClickListener.OnItemViewClickListener {
        override fun onItemViewClick(movie: Movie) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {

                val bundle = Bundle()
                bundle.putParcelable(DetailsFragment.KEY_MOVIE, movie)
                manager.beginTransaction()
                    .replace(R.id.fragment_container, DetailsFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }


        }
    })
    lateinit var viewModel: NowPlayingViewModel
    var _binding: NowPlayingFragmentBinding? = null
    val binding: NowPlayingFragmentBinding
        get() :NowPlayingFragmentBinding {
            return _binding!!
        }

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
        viewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        val observer =
            Observer<Any> { Toast.makeText(context, "Работает", Toast.LENGTH_LONG).show() }
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getNewMovies()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        nowPlayingAdapter.removeListener()
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
        nowPlayingAdapter.setMovie(appState.dataMovies)
        binding.recyclerViewNow.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewNow.adapter = nowPlayingAdapter
    }

}