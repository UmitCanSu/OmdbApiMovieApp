package com.example.filmapp.model

import androidx.room.Entity
import javax.inject.Inject

data class MovieDetail
@Inject constructor(
    val title: String?,
    val year: String?,
    val rated: String?,
    val released: String?,
    val director: String?,
    val actors: String?,
    val language: String?,
    val country: String?,
    val awards: String?,
    val poster: String,
    val imdbRating: String?,
    val imdbID: String,
    val type: String?,
)
