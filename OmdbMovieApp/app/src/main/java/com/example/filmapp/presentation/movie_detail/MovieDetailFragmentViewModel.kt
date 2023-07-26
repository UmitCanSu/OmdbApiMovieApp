package com.example.filmapp.presentation.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmapp.domain.usecase.get_movie_detail.GetMovieDetailUseCase
import com.example.filmapp.model.Movie
import com.example.filmapp.remote.repository.MovieRepositoryImpl
import com.example.filmapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailFragmentViewModel
@Inject
constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val repository: MovieRepositoryImpl
) :
    ViewModel() {
    private val _state = MutableStateFlow<MovieDetailState>(MovieDetailState.Idle)
    val state = _state

    fun searchMovieWithOmdbID(imdbId: String) {
        viewModelScope.launch {
            getMovieDetailUseCase.executeGetMovieDetail(imdbId).collect { resource ->
                when (resource) {
                    is Resource.Idle -> _state.value = MovieDetailState.Idle
                    is Resource.Loading -> _state.value = MovieDetailState.Loading
                    is Resource.Error -> _state.value = MovieDetailState.Error(resource.message!!)
                    is Resource.Success -> _state.value = MovieDetailState.Success(resource.data!!)
                }
            }
        }
    }

    private fun insertMovie(movie: Movie) {
        viewModelScope.launch {
            repository.insertMovie(movie)
            // This code block use Db to save just 10 movie
            if (repository.getMovieCount() > 10) {
                repository.deleteFirsMovie()
            }
        }
    }

}