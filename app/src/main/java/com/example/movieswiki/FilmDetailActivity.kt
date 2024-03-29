package com.example.movieswiki

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.example.movieswiki.databinding.ActivityFilmDetailBinding
import com.example.movieswiki.model.MovieViewModel

class FilmDetailActivity : AppCompatActivity() {

    companion object  {
        const val EXTRA_MOVIE = "FilmDetailActivity:movie"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra(EXTRA_MOVIE, MovieViewModel::class.java)
        if(movie != null) {
            setTitle(movie.title)
            binding.titleTextView.text = movie.title
            binding.descriptionTextView.text = movie.summary
            binding.detailnfoTextView.text = buildSpannedString {
                bold { append("Original language: ")}
                appendLine(movie.language)

                bold { append("Votes: ")}
                appendLine(movie.votes.toString())
            }
            Glide.with(this)
                .load(movie.getCoverPath())
                .into(binding.backdropImage)
        }
    }
}