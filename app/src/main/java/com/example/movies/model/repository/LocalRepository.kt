package com.example.movies.model.repository

import com.example.movies.model.Movie

interface LocalRepository {

    fun getNowPlayingMovie(): List<Movie>
    fun getUpcomingMovie(): List<Movie>
    fun getPopularMovie(): List<Movie>
}