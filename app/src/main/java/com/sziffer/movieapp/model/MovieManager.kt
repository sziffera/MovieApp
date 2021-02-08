package com.sziffer.movieapp.model

import android.util.Log
import com.sziffer.movieapp.utils.API_KEY
import com.sziffer.movieapp.MovieRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieManager {

    var movie: MovieDetails? = null

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val myInterface = retrofit.create(MovieAPI::class.java)

    const val POSTER_BASE = "https://image.tmdb.org/t/p/w185/"

    fun fetchMoviesRetrofit(searchText: String, recyclerViewAdapter: MovieRecyclerViewAdapter) {

        val call = myInterface.searchMovies(
            API_KEY,
            searchText
        )
        call.enqueue(
            object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.body() == null)
                        return

                    for (movie in response.body()!!.movies) {
                        myInterface.getMovieDetail(
                            movie.id.toString(),
                            API_KEY
                        ).enqueue(object : Callback<MovieDetails> {
                            override fun onResponse(
                                call: Call<MovieDetails>,
                                response: Response<MovieDetails>
                            ) {
                                if (response.body() == null)
                                    return
                                recyclerViewAdapter.addMovie(response.body()!!)
                            }
                            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                                Log.d("MAIN", t.localizedMessage.toString())
                            }
                        })
                    }
                }
                override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {
                    Log.d("MAIN", t.localizedMessage.toString())
                }
            }
        )
    }
}