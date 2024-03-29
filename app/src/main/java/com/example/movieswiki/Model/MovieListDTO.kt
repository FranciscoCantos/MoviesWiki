package com.example.movieswiki.Model

import com.google.gson.annotations.SerializedName

data class MovieListDTO(
    val page: Int,
    val results: List<MovieDTO>,
    val total_pages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)