package com.example.movies.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID = "id"
const val TITLE = "title"
const val VOTE_AVERAGE = "vote_average"
const val POSTER_PATH = "poster_path"

@Entity
data class HistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Long,
    @ColumnInfo(name = TITLE)
    val title: String,
    @ColumnInfo(name = VOTE_AVERAGE)
    val vote_average: Double,
    @ColumnInfo(name = POSTER_PATH)
    val poster_path: String,
    val date_request: String
)