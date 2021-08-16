package com.example.movies.view

import android.os.Handler
import com.example.movies.BuildConfig
import com.example.movies.model.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MovieLoader(
    private val listener: MovieLoaderListener,
    private val nameOfFragment: String
) {
    fun loadMovie() {
        val handler = Handler()
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
                    URL("${THE_MOVIE_DB_URL}${typeOfCategoryMovie}?api_key=${API_KEY}&language=${LANGUAGE}&page=${PAGE}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val movieListDTO: MovieListDTO = Gson().fromJson(buffer, MovieListDTO::class.java)
                handler.post(Runnable { listener.onLoaded(movieListDTO) })
            } catch (e: Exception) {
                handler.post(Runnable { listener.onFailed(e) })
            }
        }.start()
    }
}

interface MovieLoaderListener {
    fun onLoaded(movieListDTO: MovieListDTO)
    fun onFailed(throwable: Throwable)
}