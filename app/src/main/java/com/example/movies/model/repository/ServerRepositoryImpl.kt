package com.example.movies.model.repository

import com.example.movies.model.MovieDetailsDTO
import com.example.movies.model.MovieListDTO
import retrofit2.Callback

class ServerRepositoryImpl(private val remoteDataSource: RemoteDataSource) : ServerRepository {

    override fun getNowMovies(callback: Callback<MovieListDTO>) {
        remoteDataSource.getNowMovies(callback)
    }

    override fun getUpcomingMovies(callback: Callback<MovieListDTO>) {
        remoteDataSource.getUpcomingMovies(callback)
    }

    override fun getPopularMovies(callback: Callback<MovieListDTO>) {
        remoteDataSource.getPopularMovies(callback)
    }

    override fun getMovieDetails(movieId:Int, callback: Callback<MovieDetailsDTO>) {
        remoteDataSource.getMovieDetails(movieId,callback)
    }
}