package com.example.filmapp.model

import javax.inject.Inject

data class Error
@Inject constructor(
    val message: String?,
    val throwable: Throwable?
)
