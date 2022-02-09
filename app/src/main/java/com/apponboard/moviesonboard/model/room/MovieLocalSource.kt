package com.apponboard.moviesonboard.model.room

import android.util.Log
import com.apponboard.moviesonboard.logging.TAGs
import com.apponboard.moviesonboard.model.LocalSource
import com.apponboard.moviesonboard.model.Movie
import kotlinx.coroutines.*
import javax.inject.Inject

class MovieLocalSource @Inject constructor(
    private val movieDao: MovieDao
) : LocalSource {

    override suspend fun retrieveData(category:String): List<Movie> = withContext(Dispatchers.IO){
        val movies = movieDao.getMovies()

        Log.d(TAGs.roomTag, "Retrieved ${movies.size} items from database")

        if (movies.isNotEmpty()) movies
        else emptyList()
    }

    override suspend fun refreshData(movies: List<Movie>) {
        movieDao.insert(movies).size
    }
}