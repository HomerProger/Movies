package com.example.movies.utils

import com.example.movies.model.MovieDTO
import com.example.movies.model.MovieListDTO

fun convertMovieListDtoToModel(movieListDTO: MovieListDTO?): MutableList<MovieDTO> {

    val movieList: MutableList<MovieDTO> = mutableListOf()
    for (i in movieListDTO!!.results) {
        movieList.add(i)
    }
    return movieList
}