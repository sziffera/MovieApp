package com.sziffer.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.sziffer.movieapp.databinding.ActivityMainBinding
import com.sziffer.movieapp.model.MovieManager
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: MovieRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerViewAdapter = MovieRecyclerViewAdapter(this, ArrayList())

        with(binding.moviesRecyclerView){
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = recyclerViewAdapter
        }

        binding.searchImageButton.setOnClickListener {
            searchMovieButtonPressed()
        }
    }

    private fun searchMovieButtonPressed() {
        if (binding.searchEditText.text.isEmpty()) {
            return
        }
        recyclerViewAdapter.clearMovieList()
        val searchText = binding.searchEditText.text.toString().replace(' ', '+')
        MovieManager.fetchMoviesRetrofit(searchText, recyclerViewAdapter)
    }


}