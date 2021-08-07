package com.example.movies.model

class RepositoryImpl : Repository {
//    override fun getMovieFromServer(): Movie {
//        return Movie()
//    }

    override fun getNowPlayingMovie(): Array<Movie> {
        return getNowPlayingMovie_()
    }

    override fun getUpcomingMovie(): Array<Movie> {
        return getUpcomingMovie_()
    }

    override fun getPopularMovie(): Array<Movie> {
        return getPopularMovie_()
    }


}