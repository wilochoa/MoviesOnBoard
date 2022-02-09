package com.apponboard.moviesonboard.model.remote

interface NetworkUrl {
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val POPULAR = "popular"
        const val TOPRATED = "top_rated"
        const val UPCOMING = "upcoming"
        const val BASE_URL_IMG = "https://image.tmdb.org/t/p/"
        const val SMALL_SIZE = "w342"
        const val BIG_SIZE = "w780"
        const val ORIGINAL_SIZE = "original"
        const val API_KEY = "7d7b51fad1d5c3a2eac30b85407bd9fa"
    }
}