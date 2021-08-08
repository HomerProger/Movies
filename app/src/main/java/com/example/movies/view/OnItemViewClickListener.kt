package com.example.movies.view

import com.example.movies.model.Movie

interface OnItemViewClickListener {
    fun onItemViewClick(movie: Movie)
}