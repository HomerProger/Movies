package com.example.movies.view.fragments


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.MovieDTO
import com.example.movies.view.OnItemViewClickListener
import com.squareup.picasso.Picasso


class NowPlayingAdapter(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<NowPlayingAdapter.NowViewHolder>() {

    private var movieData: MutableList<MovieDTO> = mutableListOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(list: MutableList<MovieDTO>) {
        movieData = list
        notifyItemChanged(itemCount)
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
        fun init(movieDTO: MovieDTO) {
            with(itemView) {
                findViewById<TextView>(R.id.nowPlayingRecyclerItemTextView).text =
                    movieDTO.title

                Picasso.get()
                    .load("https://image.tmdb.org/t/p/original${movieDTO.poster_path}")
                    .into(findViewById<ImageView>(R.id.imageViewNowPlaying))


                findViewById<TextView>(R.id.nowPlayingRecyclerItemTextView2).text =
                    movieDTO.release_date
                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(movieDTO)
                }
            }
        }
    }
}