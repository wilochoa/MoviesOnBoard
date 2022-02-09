package com.apponboard.moviesonboard.model

interface MoviesLoader {
    suspend fun getMovieList(category:String): List<Movie>
}