package com.example.movies.viewmodel

import com.example.movies.model.MovieDTO
import com.example.movies.model.MovieDetailsDTO
import com.example.movies.room.HistoryEntity

sealed class AppState {
    data class Success(val dataMovies: MutableList<MovieDTO>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
    data class SuccessDetails(val dataDetails: MovieDetailsDTO): AppState()
    data class SuccessHistoryRoom(val dataHistory:List<HistoryEntity>):AppState()
}
