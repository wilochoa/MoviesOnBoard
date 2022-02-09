package com.apponboard.moviesonboard.dagger.dagger

import com.apponboard.moviesonboard.model.RemoteSource
import com.apponboard.moviesonboard.model.remote.IMoviesApi
import com.apponboard.moviesonboard.model.remote.MoviesRemoteSource
import com.apponboard.moviesonboard.model.remote.NetworkUrl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [RemoteModule::class])
abstract class RemoteSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteSource(moviesRemoteSource: MoviesRemoteSource): RemoteSource
}

@Module
class RemoteModule{

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(NetworkUrl.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    internal fun provideAPI(retrofit: Retrofit): IMoviesApi = retrofit.create(IMoviesApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteSource(api: IMoviesApi): MoviesRemoteSource = MoviesRemoteSource(api)
}
