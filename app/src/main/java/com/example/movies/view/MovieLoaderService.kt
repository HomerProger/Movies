package com.example.movies.view

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.movies.model.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

const val MOVIE_LIST_INTENT_FILTER = "MOVIE_LIST_INTENT_FILTER"
const val MOVIE_LIST_NOW = "MOVIE_LIST_NOW"

class MovieLoaderService(name: String = "name") : IntentService(name) {

    private val broadcastIntent = Intent(MOVIE_LIST_INTENT_FILTER)
    override fun onHandleIntent(p0: Intent?) {
        p0?.let {
            loadMovie(it.getStringExtra(MOVIE_LIST_NOW)!!)
        }
    }

    private fun loadMovie(nameOfFragment: String) {
        Thread {

            try {
                lateinit var typeOfCategoryMovie: String
                when (nameOfFragment) {
                    "NOW" -> typeOfCategoryMovie = "now_playing"
                    "POPULAR" -> typeOfCategoryMovie = "popular"
                    "UPCOMING" -> typeOfCategoryMovie = "upcoming"

                }
//               val url = URL("${THE_MOVIE_DB_URL}${typeOfCategoryMovie}?api_key=${BuildConfig.MOVIE_API_KEY}&language=${LANGUAGE}&page=${PAGE}")
                val url =
                    URL("$THE_MOVIE_DB_URL${typeOfCategoryMovie}?api_key=$API_KEY&language=$LANGUAGE&page=$PAGE")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val movieListDTO: MovieListDTO = Gson().fromJson(buffer, MovieListDTO::class.java)
                broadcastIntent.putExtra("MOVIE_LIST_$nameOfFragment", movieListDTO)
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
            } catch (e: Exception) {

            }
        }.start()
    }
}