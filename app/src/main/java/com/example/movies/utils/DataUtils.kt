package com.example.movies.utils


import com.example.movies.model.MovieDTO
import com.example.movies.model.MovieDetailsDTO
import com.example.movies.model.MovieListDTO
import com.example.movies.room.HistoryEntity
import java.text.SimpleDateFormat
import java.util.*

fun convertMovieListDtoToModel(movieListDTO: MovieListDTO?): MutableList<MovieDTO> {

    val movieList: MutableList<MovieDTO> = mutableListOf()
    for (i in movieListDTO!!.results) movieList.add(i)
    return movieList
}

fun convertModelToEntity(movieDetails: MovieDetailsDTO): HistoryEntity {
    return HistoryEntity(
        movieDetails.id,
        movieDetails.title,
        movieDetails.vote_average,
        movieDetails.poster_path,
        getCurrentDate()
    )

}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    return sdf.format(Date())
}