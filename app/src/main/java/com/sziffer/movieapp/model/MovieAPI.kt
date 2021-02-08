package com.sziffer.movieapp.model


import com.sziffer.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = API_KEY,
    ): Call<MovieDetails>
}
