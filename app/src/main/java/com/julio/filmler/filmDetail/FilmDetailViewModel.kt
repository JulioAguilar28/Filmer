package com.julio.filmler.filmDetail

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.julio.filmler.network.Film
import kotlinx.coroutines.*

class FilmDetailViewModel(film: Film, application: Application) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _filmClicked = MutableLiveData<Film>()
    val filmClicked: LiveData<Film>
        get() = _filmClicked

    init {
        _filmClicked.value = film
    }

    override fun onCleared() {
        super.onCleared()
    }
}