package com.example.movies.model


import android.os.Parcelable

import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class MovieListDTO(
    var dates: @RawValue Dates? = null,
    val page: @RawValue Int,
    val results: @RawValue Array<MovieDTO>,
    val total_pages: @RawValue Int,
    val total_results: @RawValue Int
) : Parcelable

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
