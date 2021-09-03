package com.example.movies.room

import androidx.room.*

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequest(historyEntity: HistoryEntity)

    @Query("SELECT*FROM HistoryEntity")
    fun getAllRequest(): List<HistoryEntity>
}