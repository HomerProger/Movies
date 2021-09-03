package com.example.movies.app

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.movies.room.HistoryDao
import com.example.movies.room.HistoryDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private var nameDb = "HistoryDB1"
        fun getHistoryDao(): HistoryDao {
            if (db == null) {
                val builder = Room.databaseBuilder(
                    appInstance!!.applicationContext, HistoryDataBase::class.java,
                    nameDb
                )
                db = builder.build()
            }
            return db!!.historyDao()
        }
    }
}