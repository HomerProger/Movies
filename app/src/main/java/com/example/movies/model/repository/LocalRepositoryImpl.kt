package com.example.movies.model.repository

import com.example.movies.model.getNowPlayingMovie_
import com.example.movies.model.getPopularMovie_
import com.example.movies.model.getUpcomingMovie_

class LocalRepositoryImpl : LocalRepository {


    override fun getNowPlayingMovie() = getNowPlayingMovie_()
    override fun getUpcomingMovie() = getUpcomingMovie_()
    override fun getPopularMovie() = getPopularMovie_()
}