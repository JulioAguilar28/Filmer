package com.julio.filmler.filmOverview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.julio.filmler.network.Film
import com.julio.filmler.network.TMDBApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, DONE, ERROR }

class FilmOverviewViewModel : ViewModel() {

    val API_KEY = "bcca94f271dbf6d7595b28f163b8713d"

    private val _listFilm = MutableLiveData<List<Film>>()
    val listFilm: LiveData<List<Film>>
        get() = _listFilm

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _navigateToFilmDetail = MutableLiveData<Film>()
    val navigateToFilmDetail: LiveData<Film>
        get() = _navigateToFilmDetail

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        coroutineScope.launch {
            val getPopularMoviesDeferred = TMDBApi.TMBDService.getPopularMoviesString(API_KEY)
            try {
                _status.value = ApiStatus.LOADING
                val response = getPopularMoviesDeferred.await()
                _status.value = ApiStatus.DONE
                _listFilm.value = response.results
            } catch(e: Exception) {
                _status.value = ApiStatus.ERROR
                _listFilm.value = ArrayList()
            }
        }
    }

    fun navigateToFilmDetail(film: Film) {
        _navigateToFilmDetail.value = film
        Log.i("FilmClicked", "FilmSelected: ${film.title}")
    }

    fun navigateToFilmDetailComplete() {
        _navigateToFilmDetail.value = null
        Log.i("FilmNull", "FilmSelected: ${_navigateToFilmDetail.value.toString()}")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}