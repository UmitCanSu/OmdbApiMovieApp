package com.example.filmapp.presentation.movie_list

import com.example.filmapp.model.Movie

sealed class MovieListState {
    object Idle : MovieListState()
    object Loading : MovieListState()
    class Error(val message: String) : MovieListState()
    class Success(val movieList: List<Movie>) : MovieListState()
}