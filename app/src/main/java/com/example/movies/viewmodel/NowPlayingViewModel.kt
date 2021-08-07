package com.example.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.Repository
import com.example.movies.model.RepositoryImpl
import java.lang.Thread.sleep

class NowPlayingViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    val repository: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveDataObserver
    fun getNewMovies() = getDataFromLocalSource()
    fun getDataFromLocalSource() {
        Thread {
            liveDataObserver.postValue(AppState.Success(repository.getNowPlayingMovie()))
        }.start()
    }
}
