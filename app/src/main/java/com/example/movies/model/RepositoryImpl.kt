package com.example.movies.model

import com.example.movies.view.MovieLoader
import com.example.movies.view.MovieLoaderListener

class RepositoryImpl : Repository {


    override fun getNowPlayingMovie() = getNowPlayingMovie_()
    override fun getUpcomingMovie() = getUpcomingMovie_()
    override fun getPopularMovie() = getPopularMovie_()
}