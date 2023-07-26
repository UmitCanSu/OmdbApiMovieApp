package com.example.filmapp.repository

import com.example.filmapp.remote.OmdbApi
import com.example.filmapp.remote.dto.MovieDetailDto
import com.example.filmapp.remote.dto.MovieListDto
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

/*
class OmdApiHelperImp
@Inject  constructor(val omdbApiService: OmdbApi) : OmdbApiHelper {
    override fun getMoviesWithOmdbID(omdbID: String) {
       // return omdbApiService.getMovieWithName(omdbID)
    }

    override fun getMoviesWithName(movieName: String): {
      //  return omdbApiService.getMovieWithName(movieName)
    }


}
,*/
class OmdApiHelperImp()