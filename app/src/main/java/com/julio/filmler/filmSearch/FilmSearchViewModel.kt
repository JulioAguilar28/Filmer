package com.julio.filmler.filmSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.julio.filmler.network.Film
import com.julio.filmler.network.TMDBApi
import kotlinx.coroutines.*

enum class ApiSearchStatus { LOADING, DONE, ERROR, NOTFOUND }

class FilmSearchViewModel : ViewModel() {
    val API_KEY = "bcca94f271dbf6d7595b28f163b8713d"

    private val _listSearchMovies = MutableLiveData<List<Film>>()
    val listSearchMovies: LiveData<List<Film>>
        get() = _listSearchMovies

    private val _navigateToDetail = MutableLiveData<Film>()
    val navigateToDetail: LiveData<Film>
        get() = _navigateToDetail

    private val _statusApi = MutableLiveData<ApiSearchStatus>()
    val statusApi: LiveData<ApiSearchStatus>
        get() = _statusApi

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getSearchMovies(movieInput: String) {
        val getSearchedMoviesDeferred = TMDBApi.TMBDService.getMovieSearched(API_KEY, movieInput)
        coroutineScope.launch {
            try {
                _statusApi.value = ApiSearchStatus.LOADING
                val resultList = getSearchedMoviesDeferred.await()
                _statusApi.value = ApiSearchStatus.DONE
                if(resultList.results?.size == 0) {
                    _statusApi.value = ApiSearchStatus.NOTFOUND
                }
                _listSearchMovies.value = resultList.results
            } catch(e: Exception) {
                _statusApi.value = ApiSearchStatus.ERROR
                _listSearchMovies.value = ArrayList()
            }
        }
    }

    fun navigateToDetail(film: Film) {
        _navigateToDetail.value = film
    }

    fun navigateToDetailComplete() {
        _navigateToDetail.value = null
    }
}
