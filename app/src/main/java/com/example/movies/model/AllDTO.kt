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
    var adult: Boolean = false,
    var backdrop_path: String = "",
    var belongs_to_collection: BelongToCollection? = null,
    var budget: Long = 0,
    var genres: Array<Genre> = emptyArray(),
    var homepage: String = "",
    var id: Long = 0,
    var imdb_id: String = "",
    var original_language: String = "",
    var original_title: String = "",
    var overview: String = "",
    var popularity: Float = 0.0F,
    var poster_path: String = "",
    var production_companies: Array<ProductionCompany> = emptyArray(),
    var production_countries: Array<ProductionCountry> = emptyArray(),
    var release_date: String = "",
    var revenue: Long = 0,
    var runtime: Int = 0,
    var spoken_languages: Array<SpokenLanguage> = emptyArray(),
    var status: String = "",
    var tagline: String = "",
    var title: String = "",
    var video: Boolean = false,
    var vote_average: Double = 0.0,
    var vote_count: Long = 0
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