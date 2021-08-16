package com.example.movies.view

import com.example.movies.model.MovieDTO

interface OnItemViewClickListener {
    fun onItemViewClick(movieDTO: MovieDTO)
}