package com.example.movies.model.repository

import com.example.movies.model.MovieDetailsDTO
import com.example.movies.model.MovieListDTO
import retrofit2.Callback

interface ServerRepository {

    fun getNowMovies(callback: Callback<MovieListDTO>)

    fun getUpcomingMovies(callback: Callback<MovieListDTO>)

    fun getPopularMovies(callback: Callback<MovieListDTO>)

    fun getMovieDetails(movieId: Int, callback: Callback<MovieDetailsDTO>)
}