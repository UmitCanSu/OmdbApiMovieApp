package com.example.filmapp.remote

import com.example.filmapp.until.Constant
import com.example.filmapp.remote.dto.MovieDetailDto
import com.example.filmapp.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("?")
    suspend fun getMoviesWithOmdbID(
        @Query("i") omdbID: String,
        @Query("apikey") apiKey: String = Constant.API_KEY
    ): MovieDetailDto

    @GET("?")
    suspend fun getMovieWithName(
        @Query("s") movieName: String,
        @Query("apikey") apiKey: String = Constant.API_KEY
    ): MovieListDto
}
