package com.example.movies.view.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.Movie
import com.example.movies.view.OnItemViewClickListener


class UpcomingAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    private var movieData: List<Movie> = listOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(list: List<Movie>) {
        movieData = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.upcoming_fragment_rv_item, parent, false)
        return UpcomingViewHolder(view)

    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.init(movieData[position])
    }

    override fun getItemCount() = movieData.size


    inner class UpcomingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: Movie) {
            with(itemView) {
                findViewById<TextView>(R.id.upcomingRecyclerItemTextView).text =
                    movie.movieNameRus

                findViewById<ImageView>(R.id.imageViewUpcoming).setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, movie.poster)
                )
                findViewById<TextView>(R.id.upcomingRecyclerItemTextView2).text =
                    movie.yearOfMovie
                setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
            }
        }
    }
}