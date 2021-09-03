package com.example.movies.view.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.room.HistoryEntity

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    private var data: List<HistoryEntity> = arrayListOf()


    fun setData(data: List<HistoryEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.history_fragment_rv_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: HistoryEntity) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                with(itemView) {
                    findViewById<TextView>(R.id.historyRecyclerItemTitle).text = data.title
                    findViewById<TextView>(R.id.historyRecyclerItemVoteAverage).text =
                        "Рейтинг ${data.vote_average}"
                    findViewById<TextView>(R.id.historyRecyclerItemDate).text =
                        "Дата запроса ${data.date_request}"
                    Glide.with(findViewById<ImageView>(R.id.imageViewHistory))
                        .load("https://image.tmdb.org/t/p/original${data.poster_path}")
                        .into(findViewById<ImageView>(R.id.imageViewHistory))
                }
            }
        }
    }
}