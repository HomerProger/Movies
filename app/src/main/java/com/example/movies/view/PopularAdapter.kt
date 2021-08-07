package com.example.movies.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.Movie


class PopularAdapter(var onItemViewClickListener: OnItemViewClickListener.OnItemViewClickListener?) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private var movieData: Array<Movie> = arrayOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(array: Array<Movie>) {
        movieData = array
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

    override fun getItemCount(): Int {
        return movieData.size
    }


    inner class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: Movie) {
            itemView.findViewById<TextView>(R.id.popularRecyclerItemTextView).text =
                movie.movieNameRus

            itemView.findViewById<ImageView>(R.id.imageViewPopular).setImageDrawable(
                ContextCompat.getDrawable(itemView.context, movie.poster)
            )
            itemView.findViewById<TextView>(R.id.popularRecyclerItemTextView2).text =
                movie.yearOfMovie
            itemView.setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
        }
    }
}