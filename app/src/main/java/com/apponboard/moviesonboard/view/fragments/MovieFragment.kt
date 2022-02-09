package com.apponboard.moviesonboard.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apponboard.moviesonboard.R
import com.apponboard.moviesonboard.dagger.dagger.view_model_modules.ViewModelFactory
import com.apponboard.moviesonboard.viewmodel.DaggerViewModel
import com.apponboard.moviesonboard.dagger.extensions.injectViewModel
import com.apponboard.moviesonboard.databinding.FragmentMoviesBinding
import com.apponboard.moviesonboard.logging.TAGs.Companion.EXTRA_MOVIE
import com.apponboard.moviesonboard.model.Movie
import com.apponboard.moviesonboard.view.MovieDetailActivity
import com.apponboard.moviesonboard.view.adapters.MovieParentAdapter
import com.google.android.material.snackbar.Snackbar

import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class MovieFragment(category: String) : DaggerFragment() {
    private lateinit var binding: FragmentMoviesBinding

    private lateinit var newsRecycler: RecyclerView
    private lateinit var mainAdapter: MovieParentAdapter
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var progressBar: ProgressBar
    private var requestCategory = category


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DaggerViewModel

    private val yCoordinate = "y"

    companion object Factory {
        fun create(category:String): MovieFragment {
            return MovieFragment(category)
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)

        binding = FragmentMoviesBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        initUI()

        observeMoviesData()

        scrollToPreviousPosition(savedInstanceState)

        return binding.root
    }

    private fun initUI() {
        progressBar = binding.progressBar

        newsRecycler = binding.moviesRecyclerView.also {
            with(it) {
                layoutManager = GridLayoutManager(context,2)
                itemAnimator = DefaultItemAnimator()
            }
        }

        nestedScrollView = binding.nestedScrollView
    }

    private fun observeMoviesData() {
        viewModel.loadData(requestCategory)
        viewModel.getMoviesList().observe(viewLifecycleOwner
        ) {
            it?.let {
                progressBar.visibility = View.GONE
                if (it == ArrayList(Collections.EMPTY_LIST)) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.check_connection),
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    drawRecyclerView(it)
                }
            }
        }
    }

    private fun drawRecyclerView(movies: ArrayList<Movie>) {
        mainAdapter = MovieParentAdapter(movies)
        newsRecycler.adapter = mainAdapter
        mainAdapter.notifyDataSetChanged()
        mainAdapter.onItemClick = { movie->
            run {
                Log.d("TAG_TEST", movie.overview)
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(EXTRA_MOVIE, movie)
                startActivity(intent)
            }
        }
    }

    private fun scrollToPreviousPosition(bundle: Bundle?) {
        bundle?.let {
            Handler().postDelayed({
                nestedScrollView.scrollTo(0, it.getInt(yCoordinate))
            }, 700)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(yCoordinate, nestedScrollView.scrollY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.getMoviesList().removeObservers(viewLifecycleOwner)
    }
}