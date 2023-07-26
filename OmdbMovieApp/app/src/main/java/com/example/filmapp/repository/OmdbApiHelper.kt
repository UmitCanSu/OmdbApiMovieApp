package com.example.filmapp.repository

import com.example.filmapp.remote.dto.MovieDetailDto
import com.example.filmapp.remote.dto.MovieListDto
import kotlinx.coroutines.flow.Flow

interface OmdbApiHelper {
    fun getMoviesWithOmdbID(omdbID: String): MovieDetailDto
    fun getMoviesWithName(movieName: String): MovieListDto
}