package com.apponboard.moviesonboard.model

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject


class ModelRepository @Inject constructor(
    private val netManager: NetManager,
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : MoviesLoader {

    override suspend fun getMovieList(category:String): List<Movie> {

        return if (netManager.isConnectedToInternet)
            retrieveRemoteData(category)
        else
            retrieveLocalData(category)

    }

    private suspend fun retrieveRemoteData(category: String): List<Movie> {

        val movies = remoteSource.retrieveData(category)
        localSource.refreshData(movies)
        return movies
    }

    private suspend fun retrieveLocalData(category: String): List<Movie> = localSource.retrieveData(category)
}

class NetManager @Inject constructor(private val applicationContext: Context) {

    val isConnectedToInternet: Boolean
        @SuppressLint("MissingPermission")
        get() {
            val conManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = conManager.activeNetworkInfo
            return network != null && network.isConnected
        }
}
