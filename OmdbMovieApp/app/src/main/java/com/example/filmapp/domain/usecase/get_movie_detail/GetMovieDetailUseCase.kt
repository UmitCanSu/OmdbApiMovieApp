package com.example.filmapp.domain.usecase.get_movie_detail

import com.example.filmapp.domain.MovieRepository
import com.example.filmapp.model.Movie
import com.example.filmapp.model.MovieDetail
import com.example.filmapp.until.Constant
import com.example.filmapp.until.toMovieDetail
import com.example.filmapp.until.Resource
import com.example.filmapp.until.toMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

import javax.inject.Inject

class GetMovieDetailUseCase
@Inject constructor(
    private val repository: MovieRepository
) {
    fun executeGetMovieDetail(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetailDto = repository.getMovie(imdbId)
            if (movieDetailDto.response) {
                saveMovieAndDeleteFirstMovie(movieDetailDto.toMovie())
                emit(Resource.Success(movieDetailDto.toMovieDetail()))
            } else {
                emit(Resource.Error(message = "Movie not found"))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Connection Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        } catch (e: HttpException) {
            emit(Resource.Error("Server Error"))
        }

    }

    suspend fun saveMovieAndDeleteFirstMovie(movie: Movie) {
        val movieCount = repository.getMovieCount()
        repository.insertMovie(movie)
        if (movieCount > Constant.MAX_DB_SAVED_COUNT) {
            repository.deleteFirsMovie()
        }
    }
}