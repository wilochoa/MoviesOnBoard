package com.apponboard.moviesonboard.dagger.dagger

import android.content.Context
import androidx.room.Room
import com.apponboard.moviesonboard.model.LocalSource
import com.apponboard.moviesonboard.model.room.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalModule::class])
abstract class LocalSourceModule {
    @Binds
    @Singleton
    abstract fun bindLocalSource(movieLocalSource: MovieLocalSource): LocalSource
}

@Module
class LocalModule(private val context: Context) {
    companion object {
        const val databaseName = "product_database"
    }

    @Provides
    @Singleton
    fun provideDataBase(): MovieRoomDataBase =
        Room.databaseBuilder(
            context.applicationContext,
            MovieRoomDataBase::class.java,
            databaseName
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDao(movieRoomDataBase: MovieRoomDataBase): MovieDao =
        movieRoomDataBase.movieDao()

    @Provides
    @Singleton
    fun provideLocalSource(movieDao: MovieDao): MovieLocalSource =
        MovieLocalSource(movieDao)
}