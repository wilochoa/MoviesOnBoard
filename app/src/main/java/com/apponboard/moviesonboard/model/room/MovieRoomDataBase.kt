package com.apponboard.moviesonboard.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apponboard.moviesonboard.model.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieRoomDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

}