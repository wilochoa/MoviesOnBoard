package com.apponboard.moviesonboard.model.remote

import com.apponboard.moviesonboard.model.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface IMoviesApi {
    @GET("movie/{category}")
    suspend fun getAPIMovies(@Path(value = "category") category: String,
                             @Query("api_key") api_key: String,
                             @Query("page") page: Int): Movies
}