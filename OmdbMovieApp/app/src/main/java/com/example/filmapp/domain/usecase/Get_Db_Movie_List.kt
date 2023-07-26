package com.example.filmapp.domain.usecase

import com.example.filmapp.domain.MovieRepository
import com.example.filmapp.model.Movie
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Get_Db_Movie_List
@Inject constructor(private val repository: MovieRepository) {
    fun getDbMovieList() = flow<List<Movie>> {
        emit(repository.getMovieListSortBigID())
    }
}