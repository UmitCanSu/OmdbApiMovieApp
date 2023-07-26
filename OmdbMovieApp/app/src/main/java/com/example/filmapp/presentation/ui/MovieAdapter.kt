package com.example.filmapp.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.filmapp.databinding.AdapterFilmBinding
import com.example.filmapp.until.downland
import com.example.filmapp.model.Movie
import javax.inject.Inject

class MovieAdapter
@Inject constructor(val films: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdapterFilmBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(AdapterFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = films.get(position)
        val binding = holder.binding
        connectViewWithFilm(binding, movie)
        binding.movieCard.setOnClickListener {
            val action =
                MovieListFragmentDirections.actionFilmListFragmentToMovieDetailFragment(movie.imdbID)
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun connectViewWithFilm(binding: AdapterFilmBinding, movie: Movie) {
        binding.filmPoster.downland(movie.poster!!)
        binding.filmName.text = movie.title
        binding.filmType.text = movie.type
        binding.filmYear.text = movie.year
    }
}