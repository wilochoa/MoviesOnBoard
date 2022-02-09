package com.apponboard.moviesonboard.view

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.apponboard.moviesonboard.R
import com.apponboard.moviesonboard.databinding.ActivityMovieDetailBinding
import com.apponboard.moviesonboard.logging.TAGs.Companion.EXTRA_MOVIE
import com.apponboard.moviesonboard.model.Movie
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MovieDetailActivity : DaggerAppCompatActivity(), HasAndroidInjector {

    private lateinit var binding: ActivityMovieDetailBinding

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun androidInjector(): AndroidInjector<Any> =
        dispatchingAndroidInjector as AndroidInjector<Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        val movie = intent.getSerializableExtra(EXTRA_MOVIE) as? Movie
        if (movie != null) {
            binding.movie = movie
            binding.executePendingBindings()
            Log.i(EXTRA_MOVIE,movie.overview)
        }
        initUI()

    }

    private fun initUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        val tf: Typeface? = ResourcesCompat.getFont(this, R.font.carme);
        binding.collapsingToolbar.setCollapsedTitleTypeface(tf)
        binding.collapsingToolbar.setExpandedTitleTypeface(tf)
    }
}
