package com.example.filmapp.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.filmapp.model.Movie

@Dao
interface MovieDao {
    @Insert
    suspend fun insertMovie(vararg movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query("Select * from Movie where imdbID = :imdbID")
    suspend fun selectMovie(imdbID: String): Movie

    @Query("Select * from Movie order by id asc")
    suspend fun getMovieListSortBigID(): List<Movie>

    @Query("Select * from Movie order by id desc")
    suspend fun getMovieListSortSmallID(): List<Movie>

    @Query("Select count(*) from Movie")
    suspend fun getMovieCount(): Int

    @Delete
    suspend fun deleteMovie(vararg movie: Movie)

    @Query("Delete from Movie where id = (Select min(id) from Movie limit 1)")
    suspend fun deleteFirsMovie()


}