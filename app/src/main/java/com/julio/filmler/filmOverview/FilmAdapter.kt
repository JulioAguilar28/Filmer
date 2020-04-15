package com.julio.filmler.filmOverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.julio.filmler.databinding.FilmItemBinding
import com.julio.filmler.network.Film

class FilmAdapter(val clickListener: FilmClickListener): ListAdapter<Film, FilmAdapter.FilmViewHolder>(DiffCallBack) {

    class FilmViewHolder(private var binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: FilmClickListener, film: Film) {
            binding.film = film
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(FilmItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(clickListener, film)
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class FilmClickListener(val clickListener: (film: Film) -> Unit) {
    fun onClick(film: Film) = clickListener(film)
}