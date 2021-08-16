package com.example.movies.model


import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

data class MovieListDTO(
    var dates: Dates? = null, val page: Int, val results: Array<MovieDTO>,
    val total_pages: Int, val total_results: Int
)

// Классы для построения DTO
data class Dates(val maximum: String, val minimum: String)

@Parcelize
data class MovieDTO(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: Array<String>,
    val id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: String,
    val vote_count: String
) : Parcelable
