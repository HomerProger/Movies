package com.example.movies.model

interface Repository {
    fun getMovieFromServer():Movie
    fun getMovieFromLocal():Movie
}