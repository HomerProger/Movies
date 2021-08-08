package com.example.movies.view

import android.view.View
import com.google.android.material.snackbar.Snackbar

class ViewExtensions {
}

fun View.showSnackBar(text: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, text, length).show()
}

fun View.showSnackBar(stringResource: Int, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, context.getString(stringResource), length).show()
}