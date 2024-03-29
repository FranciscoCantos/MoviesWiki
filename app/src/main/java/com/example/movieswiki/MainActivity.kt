package com.example.movieswiki

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.movieswiki.databinding.ActivityMainBinding
import com.example.movieswiki.model.MovieDbClient
import com.example.movieswiki.model.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

data class Person(val name: String, val age: Int)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviesAdapter = MoviesAdapter(emptyList())
        { movie ->
            Toast
                .makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT)
                .show()
        }

        binding.recyclerView.adapter = moviesAdapter

        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.listPopularMovies(apiKey)

            var movies: List<MovieViewModel> = popularMovies.results.map {
                MovieViewModel(it.title, it.poster_path)
            }

            moviesAdapter.movies = movies
            moviesAdapter.notifyDataSetChanged()
        }
    }
}