package com.example.movies.model.repository

import com.example.movies.model.*
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val movieAPI = Retrofit.Builder()
        .baseUrl(THE_MOVIE_DB_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build().create(MovieAPI::class.java)

    fun getNowMovies(callback: Callback<MovieListDTO>) {
        movieAPI.getNow(API_KEY, LANGUAGE, PAGE).enqueue(callback)
    }

    fun getUpcomingMovies(callback: Callback<MovieListDTO>) {
        movieAPI.getUpcoming(API_KEY, LANGUAGE, PAGE).enqueue(callback)
    }

    fun getPopularMovies(callback: Callback<MovieListDTO>) {
        movieAPI.getPopular(API_KEY, LANGUAGE, PAGE).enqueue(callback)
    }

    fun getMovieDetails(movieId: Int, callback: Callback<MovieDetailsDTO>) {
        movieAPI.getMovieDetails(movieId, API_KEY, LANGUAGE).enqueue(callback)
    }
}