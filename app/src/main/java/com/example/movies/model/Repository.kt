package com.example.movies.model

interface Repository {
    //    fun getMovieFromServer():Movie
    fun getNowPlayingMovie(): Array<Movie>
    fun getUpcomingMovie(): Array<Movie>
    fun getPopularMovie(): Array<Movie>
}