package com.example.filmapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class Movie
@Inject constructor(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String
) {
    @PrimaryKey(autoGenerate = true)
     var id = 0
}
