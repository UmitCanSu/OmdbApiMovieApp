package com.example.filmapp.domain.usecase.get_movie_list

import android.util.Log
import com.example.filmapp.domain.MovieRepository
import com.example.filmapp.model.Movie
import com.example.filmapp.until.toMovieList
import com.example.filmapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetMovieListUseCase
@Inject constructor(
    private val repository: MovieRepository
) {
    fun executeGetMovieList(title: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieListDto = repository.getMovieList(title)
            if (movieListDto.response) {
                emit(Resource.Success(movieListDto.toMovieList()))
            } else {
                emit(Resource.Error(message = "Movie not found"))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Connection Error"))
        } catch (e: Exception) {
            Log.e("GetMoviListUseCase",e.localizedMessage)
            emit(Resource.Error(e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error("Server Error"))
        }

    }
}