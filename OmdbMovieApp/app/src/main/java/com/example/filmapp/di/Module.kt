package com.example.filmapp.di

import android.content.Context
import androidx.room.Room
import com.example.filmapp.domain.MovieRepository
import com.example.filmapp.repository.MovieDao
import com.example.filmapp.repository.MovieDatabase
import com.example.filmapp.repository.OmdApiHelperImp
import com.example.filmapp.repository.OmdbApiHelper
import com.example.filmapp.remote.OmdbApi
import com.example.filmapp.remote.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun providerOmdHelper(): OmdbApi {
        return Retrofit.Builder()
            .baseUrl("https://omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        @ApplicationContext context: Context,
        omdbApi: OmdbApi,
        movieDao: MovieDao
    ): MovieRepository {
        return MovieRepositoryImpl(context, movieDao, omdbApi)
    }

    @Singleton
    @Provides
    fun movieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie.db"
        ).build()
    }

    @Singleton
    @Provides
    fun movieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }


}