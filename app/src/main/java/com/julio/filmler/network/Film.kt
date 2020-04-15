package com.julio.filmler.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (
    val id: String,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
) : Parcelable