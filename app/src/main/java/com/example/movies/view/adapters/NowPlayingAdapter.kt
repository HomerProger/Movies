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


class NowPlayingAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<NowPlayingAdapter.NowViewHolder>() {

    private var movieData: List<Movie> = listOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(list: List<Movie>) {
        movieData = list
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

    override fun getItemCount() = movieData.size



    inner class NowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(movie: Movie) {
            with(itemView) {
                findViewById<TextView>(R.id.nowPlayingRecyclerItemTextView).text =
                    movie.movieNameRus

                findViewById<ImageView>(R.id.imageViewNowPlaying).setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, movie.poster)
                )
                findViewById<TextView>(R.id.nowPlayingRecyclerItemTextView2).text =
                    movie.yearOfMovie
                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(movie)
                }
            }
        }
    }
}