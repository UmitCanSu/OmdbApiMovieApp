package com.example.filmapp.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmapp.domain.usecase.Get_Db_Movie_List
import com.example.filmapp.domain.usecase.get_movie_list.GetMovieListUseCase
import com.example.filmapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListFragmentViewModel
@Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getDbMovieList:Get_Db_Movie_List
) :
    ViewModel() {
    private val _state: MutableStateFlow<MovieListState> = MutableStateFlow(MovieListState.Idle)
    val state = _state

    fun searchMovieWithName(movieName: String) {
        viewModelScope.launch {
            getMovieListUseCase.executeGetMovieList(movieName)
                .catch {
                    _state.value = MovieListState.Error(it.message!!)
                }.collect { resource ->
                    when (resource) {
                        is Resource.Idle -> _state.value = MovieListState.Idle
                        is Resource.Loading -> _state.value = MovieListState.Loading
                        is Resource.Error -> {
                            _state.value = MovieListState.Error(resource.message!!)
                        }
                        is Resource.Success -> {
                            _state.value = MovieListState.Success(resource.data!!)
                        }
                    }
                }
        }
    }
    fun getLastSavedMovie(){
        viewModelScope.launch {
            getDbMovieList.getDbMovieList().collect{
                _state.value = MovieListState.Success(it)
            }
        }
    }
/*
    fun getLastMovie() {
        viewModelScope.launch {
            _state.value = MovieListState.Loading
            val lastSavedMovie = repository.getMovieListSortBigID()
            _state.value = MovieListState.Success(lastSavedMovie)
        }
    }
    */
}