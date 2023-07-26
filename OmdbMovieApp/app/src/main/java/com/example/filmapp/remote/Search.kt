package com.example.filmapp.remote

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class Search
@Inject constructor(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Poster")
    val poster: String
)