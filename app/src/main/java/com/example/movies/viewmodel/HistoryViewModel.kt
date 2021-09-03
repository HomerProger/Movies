package com.example.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.app.App
import com.example.movies.model.repository.HistoryRepositoryImpl
import com.example.movies.room.HistoryEntity
import java.util.logging.Handler

class HistoryViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: HistoryRepositoryImpl = HistoryRepositoryImpl(App.getHistoryDao())
) : ViewModel() {
    fun getLiveData() = liveDataObserver
    fun getAllHistory() {
        val handler = android.os.Handler()
        Thread {
            val dataHistoryList: List<HistoryEntity> = historyRepository.getAllHistory()
            handler.post(Runnable {
                liveDataObserver.value = AppState.SuccessHistoryRoom(dataHistoryList)
            })
        }.start()
    }
}