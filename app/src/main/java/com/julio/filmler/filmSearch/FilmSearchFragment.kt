package com.julio.filmler.filmSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.julio.filmler.R
import com.julio.filmler.databinding.FilmSearchFragmentBinding
import com.julio.filmler.filmOverview.FilmAdapter
import com.julio.filmler.filmOverview.FilmClickListener

class FilmSearchFragment : Fragment() {

    private lateinit var viewModel: FilmSearchViewModel
    private lateinit var binding: FilmSearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FilmSearchFragmentBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(FilmSearchViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.searchButton.setOnClickListener {
            onSearch()
        }

        binding.searchList.adapter = FilmAdapter(FilmClickListener {
            viewModel.navigateToDetail(it)
        })

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                this.findNavController().navigate(
                    FilmSearchFragmentDirections.actionSearchToDetail(it)
                )
                viewModel.navigateToDetailComplete()
            }
        })
        return binding.root
    }

    fun onSearch() {
        val movieInput = binding.movieInput.text.toString()
        viewModel.getSearchMovies(movieInput)
        binding.apply {
            searchList.visibility = View.VISIBLE
            searchButton.visibility = View.GONE
            textInputLayout4.visibility = View.GONE
        }
    }

}