package com.apponboard.moviesonboard.dagger.dagger

import android.content.Context
import com.apponboard.moviesonboard.model.NetManager
import dagger.*
import javax.inject.Singleton

@Module
class NetModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideNetManager(): NetManager = NetManager(context)
}