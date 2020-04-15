package com.julio.filmler.filmDetail

import android.app.usage.UsageEvents.Event.NONE
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.julio.filmler.R
import com.julio.filmler.databinding.FilmDetailFragmentBinding


class FilmDetailFragment : Fragment() {

    private lateinit var viewModel: FilmDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FilmDetailFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val filmClicked = FilmDetailFragmentArgs.fromBundle(arguments!!).clickedFilm
        val viewModelFactory = FilmDetailViewModelFactory(filmClicked, application)
        binding.setLifecycleOwner(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FilmDetailViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }
}