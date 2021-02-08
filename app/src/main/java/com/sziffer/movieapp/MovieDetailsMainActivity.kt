package com.sziffer.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import com.sziffer.movieapp.databinding.ActivityMovieDetailsMainBinding
import com.sziffer.movieapp.model.MovieManager

class MovieDetailsMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("DETAILS",MovieManager.movie.toString())

        with(binding) {
            movieTitleTextView.text = MovieManager.movie?.title
            Picasso.get().load(
                MovieManager.POSTER_BASE + MovieManager.movie?.posterPath).into(posterImageView)
            overviewTextView.text = MovieManager.movie?.overview
            budgetTextView.text = "${MovieManager.movie?.budget}$"
        }
    }
}