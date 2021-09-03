package com.example.movies.model.repository

import com.example.movies.model.MovieDetailsDTO
import com.example.movies.room.HistoryDao
import com.example.movies.room.HistoryEntity
import com.example.movies.utils.convertModelToEntity

class HistoryRepositoryImpl(private val historyDao: HistoryDao) : HistoryRepository {

    override fun getAllHistory(): List<HistoryEntity> {
        return historyDao.getAllRequest()
    }

    override fun saveEntity(movieDetails: MovieDetailsDTO) {
        historyDao.insertRequest(convertModelToEntity(movieDetails))
    }
}

