package com.example.movieswiki.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieViewModel (val title: String, val coverUrl: String, val summary: String, val language: String, val votes: Double) : Parcelable {
    fun getCoverPath() = "https://image.tmdb.org/t/p/w780/${coverUrl}"
}
