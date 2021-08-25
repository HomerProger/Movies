package com.example.movies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.MovieDetailsDTO
import com.example.movies.model.repository.RemoteDataSource
import com.example.movies.model.repository.ServerRepository
import com.example.movies.model.repository.ServerRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val serverRepository: ServerRepository = ServerRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    fun getLiveData() = liveDataObserver
    fun getMovieDetailsFromRemoteSource(movieId: Int){
        serverRepository.getMovieDetails(movieId, callBack)
    }

    private val callBack = object : Callback<MovieDetailsDTO> {

        override fun onResponse(call: Call<MovieDetailsDTO>, response: Response<MovieDetailsDTO>) {
            val serverResponse: MovieDetailsDTO? = response.body()
            Log.d("myLogs", "response.body(): ${response.body()}")

            if (response.isSuccessful && serverResponse != null) {
                liveDataObserver.postValue(AppState.SuccessDetails(serverResponse)) // FIXME проверок добавить
            } else {
                //TODO("Ответ нас не устраивает")
            }
        }

        override fun onFailure(call: Call<MovieDetailsDTO>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }
}
