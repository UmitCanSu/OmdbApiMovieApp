package com.example.filmapp.remote.repository

import android.content.Context
import com.example.filmapp.domain.MovieRepository
import com.example.filmapp.model.Movie
import com.example.filmapp.remote.OmdbApi
import com.example.filmapp.remote.dto.MovieDetailDto
import com.example.filmapp.remote.dto.MovieListDto
import com.example.filmapp.repository.MovieDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val movieDao: MovieDao,
    private val omdbAPi: OmdbApi
) : MovieRepository {

    override suspend fun getMovieList(title: String): MovieListDto {
        return omdbAPi.getMovieWithName(title)
    }

    override suspend fun getMovie(imdbID: String): MovieDetailDto {
        return omdbAPi.getMoviesWithOmdbID(imdbID)
    }

    override suspend fun getMovieListSortSmallID(): List<Movie> {
        return movieDao.getMovieListSortSmallID()
    }

    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    override suspend fun selectMovie(imdbId: String) {
        movieDao.selectMovie(imdbId)
    }

    override suspend fun getMovieListSortBigID(): List<Movie> {
        return movieDao.getMovieListSortBigID()
    }

    override suspend fun getMovieCount(): Int {
        return movieDao.getMovieCount()
    }

    override suspend fun deleteFirsMovie() {
        movieDao.deleteFirsMovie()
    }

}