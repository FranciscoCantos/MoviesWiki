package com.example.movieswiki.UI.MoviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieswiki.databinding.ViewMovieBinding
import com.example.movieswiki.Model.MovieViewModel

class MoviesAdapter(
    var movies: List<MovieViewModel>,
    private val listener: (MovieViewModel) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding(movies[position])
        holder.itemView.setOnClickListener {
            listener(movies[position])
        }
    }

    class ViewHolder(private val binding: ViewMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(movie: MovieViewModel) {
            binding.titleTextView.text = movie.title
            Glide
                .with(binding.root.context)
                .load(movie.getCoverPath())
                .into(binding.coverImageView)
        }
    }
}