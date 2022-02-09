package com.apponboard.moviesonboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apponboard.moviesonboard.model.Movie
import com.apponboard.moviesonboard.model.MoviesLoader
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class DaggerViewModel @Inject constructor(
    private val loader: MoviesLoader
) : ViewModel() {
    private val _moviesListMutable = MutableLiveData<ArrayList<Movie>>()
    private lateinit var viewModelJob: Job

     fun loadData(category:String) {
        viewModelJob = viewModelScope.launch {

            val results = loader.getMovieList(category)
            _moviesListMutable.postValue(ArrayList(results))
        }
    }

    fun getMoviesList(): LiveData<ArrayList<Movie>> = _moviesListMutable

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}