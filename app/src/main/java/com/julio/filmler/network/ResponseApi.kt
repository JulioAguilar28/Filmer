package com.julio.filmler.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseApi (
    val page: Int,
    val results: List<Film>? = null
) : Parcelable