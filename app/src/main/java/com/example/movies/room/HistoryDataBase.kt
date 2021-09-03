package com.example.movies.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}