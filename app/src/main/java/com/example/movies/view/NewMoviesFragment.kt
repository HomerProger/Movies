package com.example.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.NewMoviesFragmentBinding
import com.example.movies.viewmodel.AppState
import com.example.movies.viewmodel.MainViewModel

class NewMoviesFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    var _binding: NewMoviesFragmentBinding? = null
    val binding: NewMoviesFragmentBinding
        get() :NewMoviesFragmentBinding {
            return _binding!!
        }

    companion object {
        fun newInstance() = NewMoviesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer =
            Observer<Any> { Toast.makeText(context, "Работает", Toast.LENGTH_LONG).show() }
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getNewMovies()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> TODO()
            is AppState.Success -> {
                setData(appState)
            }
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

        }

    }

    private fun setData(appState: AppState.Success) {
        binding.loadingLayout.visibility = View.GONE
        binding.nameMovie.text = appState.dataNewMovies.movieName
        binding.dateOfPremiere.text = appState.dataNewMovies.dateOfPremiere
        binding.imagePoster.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.poster_jungle_cruise
            )
        )
    }

}