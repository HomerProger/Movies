package com.example.movies.viewmodel

import com.example.movies.model.Movie

sealed class AppState {
    data class Success(val dataMovies: Array<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
