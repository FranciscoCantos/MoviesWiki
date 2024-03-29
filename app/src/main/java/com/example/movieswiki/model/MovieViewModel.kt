package com.example.movieswiki.model

class MovieViewModel (val title: String, val coverUrl: String) {
    fun getCoverPath() = "https://image.tmdb.org/t/p/w185/${coverUrl}"
}

