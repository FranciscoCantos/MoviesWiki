package com.example.movieswiki

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.movieswiki.UI.MoviesDetail.MovieDetailActivity
import com.example.movieswiki.UI.MoviesList.MoviesAdapter
import com.example.movieswiki.databinding.ActivityMainBinding
import com.example.movieswiki.Model.MovieDbClient
import com.example.movieswiki.Model.MovieViewModel
import kotlinx.coroutines.launch

data class Person(val name: String, val age: Int)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviesAdapter = MoviesAdapter(emptyList()) {
            navigateToDetail(it)
        }

        binding.recyclerView.adapter = moviesAdapter

        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.listPopularMovies(apiKey)

            var movies: List<MovieViewModel> = popularMovies.results.map {
                MovieViewModel(it.title, it.poster_path, it.overview, it.original_language, it.vote_average)
            }

            moviesAdapter.movies = movies
            moviesAdapter.notifyDataSetChanged()
        }
    }

    private fun navigateToDetail(movie: MovieViewModel) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}