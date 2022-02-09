package com.apponboard.moviesonboard.model.room

import androidx.room.*
import com.apponboard.moviesonboard.model.Movie

@Dao
@TypeConverters(Converters::class)
interface MovieDao {
    @Query("SELECT * from movies")
    suspend fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<Movie>): List<Long>

    @Query("SELECT COUNT(*) FROM movies")
    suspend fun countOfRows(): Int
}