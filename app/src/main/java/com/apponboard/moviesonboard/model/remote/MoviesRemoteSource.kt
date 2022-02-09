package com.apponboard.moviesonboard.model.remote

import android.util.Log
import com.apponboard.moviesonboard.logging.TAGs
import com.apponboard.moviesonboard.model.Movie
import com.apponboard.moviesonboard.model.RemoteSource
import kotlinx.coroutines.*
import javax.inject.Inject

class MoviesRemoteSource @Inject constructor(
    private val api: IMoviesApi
) : RemoteSource {

    override suspend fun retrieveData(category:String): List<Movie> = withContext(Dispatchers.IO) {
        val list = api.getAPIMovies(category,NetworkUrl.API_KEY,1).results

        Log.d(TAGs.retrofitTag, "Retrieved ${list.size} items from network")

        list
    }
}