package com.example.movies.model

interface Repository {
 //   fun getMovieFromServer():List<Movie>
    fun getNowPlayingMovie(): List<Movie>
    fun getUpcomingMovie(): List<Movie>
    fun getPopularMovie(): List<Movie>
}