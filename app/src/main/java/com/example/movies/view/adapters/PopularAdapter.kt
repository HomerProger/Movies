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


class PopularAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private var movieData: MutableList<MovieDTO> = mutableListOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(list: MutableList<MovieDTO>) {
        movieData = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_fragment_rv_item, parent, false)
        return PopularViewHolder(view)

    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.init(movieData[position])
    }

    override fun getItemCount() = movieData.size

    inner class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movieDTO: MovieDTO) {
            with(itemView) {
                findViewById<TextView>(R.id.popularRecyclerItemTextView).text =
                    movieDTO.title
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/original${movieDTO.poster_path}")
                    .into(findViewById<ImageView>(R.id.imageViewPopular))

                findViewById<TextView>(R.id.popularRecyclerItemTextView2).text =
                    movieDTO.release_date
                setOnClickListener { onItemViewClickListener?.onItemViewClick(movieDTO) }
            }
        }
    }
}