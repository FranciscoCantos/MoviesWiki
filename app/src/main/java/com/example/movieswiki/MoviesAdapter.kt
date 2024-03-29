package com.example.movieswiki

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieswiki.databinding.ViewMovieBinding
import com.example.movieswiki.model.MovieModel

class MoviesAdapter(
    private val movies: List<MovieModel>,
    private val listener: (MovieModel) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewMovieBinding.inflate(LayoutInflater.from(parent.context), parent,false)
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
        fun binding(movie: MovieModel) {
            binding.titleTextView.text = movie.title
            Glide
                .with(binding.root.context)
                .load(movie.coverUrl)
                .into(binding.coverImageView)

        }
    }
}