package com.example.movies.model.repository

import com.example.movies.model.*
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET(MOVIE_LIST_NOW)
    fun getNow(
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") page:String
    ): retrofit2.Call<MovieListDTO>

    @GET(MOVIE_LIST_UPCOMING)
    fun getUpcoming(
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") page:String
    ): retrofit2.Call<MovieListDTO>

    @GET(MOVIE_LIST_POPULAR)
    fun getPopular(
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") page:String
    ): retrofit2.Call<MovieListDTO>

    @GET(MOVIE_DETAILS)
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey:String,
        @Query("language") language:String
    ): retrofit2.Call<MovieDetailsDTO>
}