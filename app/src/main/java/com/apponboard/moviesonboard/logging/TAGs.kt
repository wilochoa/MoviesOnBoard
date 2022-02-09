package com.apponboard.moviesonboard.logging

interface TAGs {
    companion object {
        const val roomTag: String = "Room retrieving"
        const val retrofitTag: String = "Retrofit retrieving"
        const val EXTRA_MOVIE:String = "EXTRA_MOVIE"
    }
}