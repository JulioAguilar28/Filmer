package com.julio.filmler.filmDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.julio.filmler.network.Film

class FilmDetailViewModelFactory (
    val film: Film,
    val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmDetailViewModel::class.java)) {
            return FilmDetailViewModel(film, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}