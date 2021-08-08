package com.example.movies.model

class RepositoryImpl : Repository {
    override fun getNowPlayingMovie() = getNowPlayingMovie_()
    override fun getUpcomingMovie() = getUpcomingMovie_()
    override fun getPopularMovie() = getPopularMovie_()
}