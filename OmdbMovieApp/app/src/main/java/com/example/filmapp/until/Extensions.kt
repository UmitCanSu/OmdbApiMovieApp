package com.example.filmapp.until

import com.bumptech.glide.Glide
import com.example.filmapp.R
import com.example.filmapp.model.Movie
import com.example.filmapp.model.MovieDetail
import com.example.filmapp.remote.dto.MovieDetailDto
import com.example.filmapp.remote.dto.MovieListDto
import com.google.android.material.imageview.ShapeableImageView

fun ShapeableImageView.downland(imgUrl: String) {
    Glide
        .with(this.context)
        .load(imgUrl)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_background)
        .into(this);

}

fun MovieListDto.toMovieList(): List<Movie> {
    return search.map { search ->
        Movie(
            search.title,
            search.year,
            search.imdbID,
            search.type,
            search.poster
        )
    }
}

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        title,
        year,
        rated,
        released,
        director,
        actors,
        language,
        country,
        awards,
        poster,
        imdbRating,
        imdbID,
        type
    )
}

fun MovieDetailDto.toMovie(): Movie {
    return Movie(
        title,
        year,
        imdbID,
        type,
        poster
    )
}
