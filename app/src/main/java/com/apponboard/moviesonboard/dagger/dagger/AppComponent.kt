package com.apponboard.moviesonboard.dagger.dagger

import android.app.Application
import com.apponboard.moviesonboard.dagger.dagger.view_model_modules.ViewModelModule
import com.apponboard.moviesonboard.model.ModelRepository
import com.apponboard.moviesonboard.model.remote.MoviesRemoteSource
import com.apponboard.moviesonboard.model.room.MovieDao
import com.apponboard.moviesonboard.model.room.MovieLocalSource
import com.apponboard.moviesonboard.view.MainActivity
import com.apponboard.moviesonboard.view.MovieDetailActivity
import com.apponboard.moviesonboard.view.fragments.MovieFragment
import com.apponboard.moviesonboard.viewmodel.DaggerViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [AndroidSupportInjectionModule::class, ViewModelModule::class,
        FragmentModule::class, ModuleRepository::class, LocalModule::class,
        RemoteModule::class, NetModule::class, ViewModule::class, LoaderModule::class,
        RemoteSourceModule::class, LocalSourceModule::class]
)
@Singleton
interface AppComponent {
    fun inject(application: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun setNetModule(netModule: NetModule): Builder

        fun setLocalModule(localModule: LocalModule): Builder

        @BindsInstance
        fun bindApplication(application: Application): Builder
    }

    fun provideDao(): MovieDao

    fun provideLocalSrc(): MovieLocalSource

    fun provideRemoteSrc(): MoviesRemoteSource

    fun provideViewModel(): DaggerViewModel

    fun provideModelRepo(): ModelRepository
}

@Module
abstract class ViewModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMainActivityAndroidInjector(): MainActivity
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeDetailActivityAndroidInjector(): MovieDetailActivity

}

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeFragmentViewModelInjector(): MovieFragment
}