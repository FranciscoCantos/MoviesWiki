package com.example.movieswiki

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.movieswiki.UI.MoviesDetail.MovieDetailActivity
import com.example.movieswiki.UI.MoviesList.MoviesAdapter
import com.example.movieswiki.databinding.ActivityMainBinding
import com.example.movieswiki.Model.MovieDbClient
import com.example.movieswiki.Model.MovieViewModel
import kotlinx.coroutines.launch
import android.Manifest
import android.annotation.SuppressLint
import android.health.connect.datatypes.ExerciseRoute
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

data class Person(val name: String, val age: Int)

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val moviesAdapter = MoviesAdapter(emptyList()) {
        navigateToDetail(it)
    }
    private val requestPermisionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
       requestPopularMovies(isGranted)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding.recyclerView.adapter = moviesAdapter

        requestPermisionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    @SuppressLint("MissingPermission")
    private fun requestPopularMovies(isLocationGranted: Boolean) {
        if (isLocationGranted) {
            fusedLocationClient.lastLocation.addOnCompleteListener {
                doRequestPopularMovies(getRegionFromLocation(it.result))
            }
        } else {
            doRequestPopularMovies("US")
        }
    }

    private fun doRequestPopularMovies(region: String) {
        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.listPopularMovies(apiKey, region)

            var movies: List<MovieViewModel> = popularMovies.results.map {
                MovieViewModel(
                    it.title,
                    it.poster_path,
                    it.overview,
                    it.original_language,
                    it.vote_average
                )
            }

            moviesAdapter.movies = movies
            moviesAdapter.notifyDataSetChanged()
        }
    }

    private fun getRegionFromLocation(location: Location?): String {
        if (location == null) {
            return "US"
        }
        val geocoder = Geocoder(this)
        val result = geocoder.getFromLocation(location.latitude, location.longitude,1)
        if (result != null) {
            return result.first().countryCode
        } else {
            return "US"
        }

    }

    private fun navigateToDetail(movie: MovieViewModel) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}