package com.example.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.MovieListDTO
import com.example.movies.model.repository.*
import com.example.movies.utils.convertMovieListDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val serverRepository: ServerRepository = ServerRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    fun getLiveData() = liveDataObserver
    fun getUpcomingMovieListFromRemoteSource(){
        serverRepository.getUpcomingMovies(callBack)
    }

    private val callBack = object : Callback<MovieListDTO> {

        override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
            val serverResponse: MovieListDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                liveDataObserver.postValue(AppState.Success(convertMovieListDtoToModel(serverResponse))) // FIXME проверок добавить
            } else {
                //TODO("Ответ нас не устраивает")
            }
        }

        override fun onFailure(call: Call<MovieListDTO>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }
}
