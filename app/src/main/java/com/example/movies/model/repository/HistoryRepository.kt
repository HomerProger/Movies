package com.example.movies.model.repository

import com.example.movies.model.MovieDetailsDTO
import com.example.movies.room.HistoryEntity

interface HistoryRepository {

    fun getAllHistory(): List<HistoryEntity>
    fun saveEntity(movieDetails: MovieDetailsDTO)
}