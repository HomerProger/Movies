package com.example.movies.viewmodel

import com.example.movies.model.MovieDTO
import com.example.movies.model.MovieDetailsDTO

sealed class AppState {
    data class Success(val dataMovies: MutableList<MovieDTO>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
    data class SuccessDetails(val dataDetails: MovieDetailsDTO): AppState()
}
