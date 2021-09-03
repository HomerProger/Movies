package com.example.movies.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.HistoryFragmentBinding
import com.example.movies.viewmodel.AppState
import com.example.movies.viewmodel.HistoryViewModel

class HistoryFragment : Fragment() {

    private var _binding: HistoryFragmentBinding? = null
    private val binding: HistoryFragmentBinding
        get() :HistoryFragmentBinding = _binding!!
    private val viewModel: HistoryViewModel by lazy { ViewModelProvider(this).get(HistoryViewModel::class.java) }
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAllHistory()

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessHistoryRoom -> {
                binding.historyList.adapter = adapter
                binding.historyList.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter.setData(appState.dataHistory)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}