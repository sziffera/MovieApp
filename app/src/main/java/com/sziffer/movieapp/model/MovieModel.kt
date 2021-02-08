package com.sziffer.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: ArrayList<MovieModel>,
    @SerializedName("total_pages") val totalPages: Int
)

data class MovieModel(
    @SerializedName("id") val id: Long
)

data class MovieDetails(
    @SerializedName("budget") val budget: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String = ""
)
