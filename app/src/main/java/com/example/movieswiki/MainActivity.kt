package com.example.movieswiki

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.movieswiki.databinding.ActivityMainBinding
import com.example.movieswiki.model.MovieDbClient
import com.example.movieswiki.model.MovieModel
import kotlin.concurrent.thread

data class Person(val name: String, val age: Int)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = MoviesAdapter(
            listOf(
                MovieModel(
                    "Titulo 1",
                    "https://www.funcion.info/wp-content/uploads/2021/03/funcion-de-las-imagenes.jpg"
                ),
                MovieModel(
                    "Titulo 2",
                    "https://www.funcion.info/wp-content/uploads/2021/03/funcion-de-las-imagenes.jpg"
                ),
                MovieModel(
                    "Titulo 3",
                    "https://www.funcion.info/wp-content/uploads/2021/03/funcion-de-las-imagenes.jpg"
                ),
                MovieModel(
                    "Titulo 4",
                    "https://www.funcion.info/wp-content/uploads/2021/03/funcion-de-las-imagenes.jpg"
                ),
                MovieModel(
                    "Titulo 5",
                    "https://www.funcion.info/wp-content/uploads/2021/03/funcion-de-las-imagenes.jpg"
                ),
                MovieModel(
                    "Titulo 6",
                    "https://www.funcion.info/wp-content/uploads/2021/03/funcion-de-las-imagenes.jpg"
                ),
                MovieModel(
                    "Titulo 7",
                    "https://www.funcion.info/wp-content/uploads/2021/03/funcion-de-las-imagenes.jpg"
                )
            )
        )
        { movie ->
            Toast
                .makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT)
                .show()
        }

        thread {
            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.listPopularMovies(apiKey)
            val result = popularMovies.execute().body()
            if (result != null) {
                Log.d("MainActivity", "Movie Count ${result.results.size}")
            }
        }
    }
}