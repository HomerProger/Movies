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
import com.example.movies.model.MovieDTO
import com.example.movies.view.OnItemViewClickListener
import com.squareup.picasso.Picasso


class UpcomingAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    private var movieData: MutableList<MovieDTO> = mutableListOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(list: MutableList<MovieDTO>) {
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
        fun init(movieDTO: MovieDTO) {
            with(itemView) {
                findViewById<TextView>(R.id.upcomingRecyclerItemTextView).text =
                    movieDTO.title

                Picasso.get()
                    .load("https://image.tmdb.org/t/p/original${movieDTO.poster_path}")
                    .into(findViewById<ImageView>(R.id.imageViewUpcoming))
                findViewById<TextView>(R.id.upcomingRecyclerItemTextView2).text =
                    movieDTO.release_date
                setOnClickListener { onItemViewClickListener?.onItemViewClick(movieDTO) }
            }
        }
    }
}