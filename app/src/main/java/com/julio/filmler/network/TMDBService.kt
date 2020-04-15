package com.julio.filmler.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

val BASE_URL = "https://api.themoviedb.org/3/"
//https://api.themoviedb.org/3/search/movie?api_key=bcca94f271dbf6d7595b28f163b8713d&&query=ad-astra

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface TMDBService {
    @GET("movie/popular")
    fun getPopularMoviesString(@Query("api_key") apiKey: String):
            Deferred<ResponseApi>

    @GET(value = "search/movie")
    fun getMovieSearched(@Query("api_key") apiKey: String, @Query("query") movieSearched: String):
            Deferred<ResponseApi>
}

object TMDBApi {
    val TMBDService : TMDBService by lazy {
        retrofit.create(TMDBService::class.java)
    }
}