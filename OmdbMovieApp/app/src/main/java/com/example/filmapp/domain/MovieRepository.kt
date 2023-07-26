package com.example.filmapp.domain

import com.example.filmapp.model.Movie
import com.example.filmapp.remote.dto.MovieDetailDto
import com.example.filmapp.remote.dto.MovieListDto

interface MovieRepository {
    suspend fun getMovieList(title: String): MovieListDto
    suspend fun getMovie(imdbID: String): MovieDetailDto
    suspend fun getMovieListSortSmallID(): List<Movie>
    suspend fun insertMovie(movie: Movie)
    suspend fun selectMovie(imdbId: String)
    suspend fun getMovieListSortBigID(): List<Movie>
    suspend fun getMovieCount(): Int
    suspend fun deleteFirsMovie()
}