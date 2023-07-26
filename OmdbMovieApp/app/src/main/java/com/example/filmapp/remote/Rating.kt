package com.example.filmapp.remote

import javax.inject.Inject

data class Rating
@Inject constructor(
    val source: String,
    val value: String
)