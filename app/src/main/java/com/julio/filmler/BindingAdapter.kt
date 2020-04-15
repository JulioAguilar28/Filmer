package com.julio.filmler

import android.media.Image
import android.opengl.Visibility
import android.view.ContextMenu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.julio.filmler.filmOverview.ApiStatus
import com.julio.filmler.filmOverview.FilmAdapter
import com.julio.filmler.filmSearch.ApiSearchStatus
import com.julio.filmler.network.Film

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Film>?) {
    val adapter = recyclerView.adapter as FilmAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imageView.context)
            .load("https://image.tmdb.org/t/p/original${imgUrl}")
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

@BindingAdapter("statusImage")
fun statusImage(statusImageView: ImageView, status: ApiStatus?) {
    when(status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_failed)
        }

        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("statusSearch")
fun setStatusSearch(statusImageView: ImageView, status: ApiSearchStatus?) {
    when(status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_failed)
        }

        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }

        ApiSearchStatus.NOTFOUND -> {
            statusImageView.setImageResource(R.drawable.ic_not_found)
            statusImageView.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("textStatus")
fun setTextStatus(textView: TextView, status: ApiSearchStatus?) {
    when(status) {
        ApiSearchStatus.LOADING -> {
            textView.visibility = View.GONE
        }

        ApiSearchStatus.DONE -> {
            textView.visibility = View.GONE
        }

        ApiSearchStatus.ERROR -> {
            textView.text = "Connection Error"
            textView.visibility = View.VISIBLE
        }

        ApiSearchStatus.NOTFOUND -> {
            textView.text = "Movie not found"
            textView.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setVoteAverageColor")
fun setAverageStatusColor(textView: TextView, voteAverage: Double) {
    val context = textView.context

    val color = when {
        voteAverage >= 8.5 -> ContextCompat.getColor(context, R.color.positive)
        voteAverage >= 6.5 -> ContextCompat.getColor(context, R.color.regular)
        else -> ContextCompat.getColor(context, R.color.negative)
    }

    textView.setTextColor(color)
}

@BindingAdapter("toString")
fun toString(textView: TextView, voteAverage: Double?) {
    val voteAverageString = voteAverage.toString()
    textView.text = voteAverageString
}