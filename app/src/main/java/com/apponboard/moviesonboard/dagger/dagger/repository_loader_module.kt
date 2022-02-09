package com.apponboard.moviesonboard.dagger.dagger

import com.apponboard.moviesonboard.model.MoviesLoader
import com.apponboard.moviesonboard.model.ModelRepository
import com.apponboard.moviesonboard.model.NetManager
import com.apponboard.moviesonboard.model.remote.MoviesRemoteSource
import com.apponboard.moviesonboard.model.room.MovieLocalSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ModuleRepository::class])
abstract class LoaderModule{
    @Binds
    @Singleton
    abstract fun bindLoader(modelRepository: ModelRepository): MoviesLoader
}

@Module(includes = [NetModule::class, RemoteModule::class, LocalModule::class])
class ModuleRepository {
    @Provides
    @Singleton
    fun provideMainRepository(
        netManager: NetManager,
        localSource: MovieLocalSource,
        remoteSource: MoviesRemoteSource
    ): ModelRepository = ModelRepository(
        netManager, localSource, remoteSource
    )
}