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

data class MovieDetailsDTO(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongToCollection,
    val budget: Long,
    val genres: Array<Genre>,
    val homepage: String,
    val id: Long,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
    val production_companies: Array<ProductionCompany>,
    val production_countries: Array<ProductionCountry>,
    val release_date: String,
    val revenue: Long,
    val runtime: Int,
    val spoken_languages: Array<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
)


data class BelongToCollection(
    val id: Long,
    val name: String,
    val poster_path: String,
    val backdrop_path: String
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Long,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)