package com.example.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.Repository
import com.example.movies.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),val repository:Repository=RepositoryImpl()) : ViewModel() {

    fun getLiveData() = liveDataObserver
    fun getNewMovies()=getDataFromLocalSource()
    fun getDataFromLocalSource() {
        Thread {
            liveDataObserver.postValue(AppState.Loading)
            sleep(2000)
            liveDataObserver.postValue(AppState.Success(repository.getMovieFromLocal()))
        }.start()
    }
}
