package com.example.filmapp.presentation.movie_detail

import com.example.filmapp.model.MovieDetail

sealed class MovieDetailState {
    object Idle : MovieDetailState()
    object Loading : MovieDetailState()
    class Error(val message: String) : MovieDetailState()
    class Success(val movieDetail: MovieDetail) : MovieDetailState()
}