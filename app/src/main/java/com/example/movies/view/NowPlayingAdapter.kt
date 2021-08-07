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


class NowPlayingAdapter(var onItemViewClickListener: OnItemViewClickListener.OnItemViewClickListener?) :
    RecyclerView.Adapter<NowPlayingAdapter.NowViewHolder>() {

    private var movieData: Array<Movie> = arrayOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(array: Array<Movie>) {
        movieData = array
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.now_playing_fragment_rv_item, parent, false)
        return NowViewHolder(view)

    }

    override fun onBindViewHolder(holder: NowViewHolder, position: Int) {
        holder.init(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }


    inner class NowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: Movie) {
            itemView.findViewById<TextView>(R.id.nowPlayingRecyclerItemTextView).text =
                movie.movieNameRus

            itemView.findViewById<ImageView>(R.id.imageViewNowPlaying).setImageDrawable(
                ContextCompat.getDrawable(itemView.context, movie.poster)
            )
            itemView.findViewById<TextView>(R.id.nowPlayingRecyclerItemTextView2).text =
                movie.yearOfMovie
            itemView.setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
        }
    }
}