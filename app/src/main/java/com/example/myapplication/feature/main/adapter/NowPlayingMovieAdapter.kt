package com.example.myapplication.feature.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.common.extension.loadImage
import com.example.myapplication.core.di.IMAGE_SOURCE
import com.example.myapplication.databinding.NowShowingItemBinding
import com.example.myapplication.feature.main.model.NowPlayingMovie

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
class NowPlayingMovieAdapter(
    val onItemClicked: (NowPlayingMovie) -> Unit
) : RecyclerView.Adapter<NowPlayingMovieAdapter.ViewHolder>() {

    private var list: ArrayList<NowPlayingMovie> = ArrayList()
    private var listFiltered: ArrayList<NowPlayingMovie> = ArrayList()

    fun addData(list: List<NowPlayingMovie>) {
        this.list = list as ArrayList<NowPlayingMovie>
        listFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NowShowingItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listFiltered.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                binding.tvTitleMovie.text = title
                binding.tvRated.text = "$vote_average/10 IMDb"
                binding.ivMovie.loadImage(IMAGE_SOURCE + poster_path)
                binding.itemLayout.setOnClickListener {
                    onItemClicked(this)
                }
            }
        }
    }

    inner class ViewHolder(val binding: NowShowingItemBinding) : RecyclerView.ViewHolder(binding.root)

}