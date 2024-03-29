package com.example.movieswiki

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.movieswiki.model.MovieModel

class MovieView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    private val cover: ImageView
    private val title: TextView

    init {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.view_movie, this, true)

        cover = view.findViewById(R.id.coverImageView)
        title = view.findViewById(R.id.titleTextView)

        orientation = VERTICAL
    }

    fun setMovie(movie: MovieModel) {
        title.text = movie.title
        //cover.image = movie.cover
    }
}