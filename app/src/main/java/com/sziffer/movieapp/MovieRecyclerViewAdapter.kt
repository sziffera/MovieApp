package com.sziffer.movieapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sziffer.movieapp.model.MovieDetails
import com.sziffer.movieapp.model.MovieManager

class MovieRecyclerViewAdapter(
    private val context: Context,
    private var movies: ArrayList<MovieDetails>
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
        private val recyclerViewOnClickListener: RecyclerViewOnClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val movieHolderLienarLayout: LinearLayout =
            itemView.findViewById(R.id.movieHolderLinearLayout)

        val budgetTextView: TextView = itemView.findViewById(R.id.movieBudgetTextView)
        val movieImageView: ImageView = itemView.findViewById(R.id.movieImageView)
        val movieTitleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)


        override fun onClick(p0: View?) {
            recyclerViewOnClickListener.itemClicked(itemView, this.layoutPosition)
        }

        init {
            movieHolderLienarLayout.setOnClickListener(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val itemView: View = LayoutInflater.from(context)
            .inflate(R.layout.movie_item,parent,false)
        return ViewHolder(itemView, object : RecyclerViewOnClickListener {
            override fun itemClicked(v: View, pos: Int) {
                MovieManager.movie = movies[pos]
                context.startActivity(
                        Intent(context, MovieDetailsMainActivity::class.java)
                )
            }
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        with(holder) {
            movieTitleTextView.text = movie.title
            Picasso.get()
                .load("${MovieManager.POSTER_BASE}${movie.posterPath}").into(movieImageView)
            budgetTextView.text = "${movie.budget} $"
        }

    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    fun addMovie(movie: MovieDetails) {
        this.movies.add(movie)
        this.notifyItemInserted(movies.size - 1)
    }

    fun clearMovieList() {
        this.movies.clear()
        this.notifyDataSetChanged()
    }
}


interface RecyclerViewOnClickListener {
    fun itemClicked(v: View, pos: Int)
}