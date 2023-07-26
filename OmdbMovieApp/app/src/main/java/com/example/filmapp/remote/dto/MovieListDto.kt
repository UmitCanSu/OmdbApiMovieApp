package com.example.filmapp.remote.dto

import com.example.filmapp.model.Movie
import com.example.filmapp.remote.Search
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class MovieListDto
@Inject constructor(
    @SerializedName("Response")
    val response: Boolean,
    @SerializedName("Search")
    val search: List<Search>,
    @SerializedName("totalResults")
    val totalResult: String
)


