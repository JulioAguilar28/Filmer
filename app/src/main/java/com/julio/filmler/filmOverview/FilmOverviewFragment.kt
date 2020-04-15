package com.julio.filmler.filmOverview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.julio.filmler.R
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.julio.filmler.databinding.FilmItemBinding
import com.julio.filmler.databinding.FilmOverviewFragmentBinding
import kotlinx.coroutines.delay


class FilmOverviewFragment : Fragment() {

    private lateinit var viewModel: FilmOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FilmOverviewFragmentBinding.inflate(inflater)

        viewModel = ViewModelProviders.of(this).get(FilmOverviewViewModel::class.java)

        binding.viewModel = viewModel

        binding.setLifecycleOwner(this)

        binding.filmList.adapter = FilmAdapter(FilmClickListener {
            viewModel.navigateToFilmDetail(it)
        })

        viewModel.navigateToFilmDetail.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                this.findNavController().navigate(
                    FilmOverviewFragmentDirections.actionOverviewToDetail(it)
                )
                viewModel.navigateToFilmDetailComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_overflow, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_movie -> {
                this.findNavController().navigate(
                    FilmOverviewFragmentDirections.actionOverviewToSearch()
                )
                true
            }

            R.id.aboutFragment -> {
                NavigationUI.onNavDestinationSelected(
                    item!!, view!!.findNavController()
                )
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
//        return NavigationUI.onNavDestinationSelected(
//            item!!, view!!.findNavController())
//                || super.onOptionsItemSelected(item)
    }
}
