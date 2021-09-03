package com.example.movies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.app.App
import com.example.movies.model.MovieDTO
import com.example.movies.model.MovieDetailsDTO
import com.example.movies.model.repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val serverRepository: ServerRepository = ServerRepositoryImpl(RemoteDataSource()),
    private val historyRepository: HistoryRepositoryImpl = HistoryRepositoryImpl(App.getHistoryDao())
) : ViewModel() {

    fun getLiveData() = liveDataObserver
    fun getMovieDetailsFromRemoteSource(movieId: Int){
        serverRepository.getMovieDetails(movieId, callBack)
    }
    fun saveMovieToDb(movieDetails: MovieDetailsDTO){
        historyRepository.saveEntity(movieDetails)
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
