package com.example.myapplication.feature.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.common.extension.loadImage
import com.example.myapplication.core.di.IMAGE_SOURCE
import com.example.myapplication.databinding.PopularItemBinding
import com.example.myapplication.feature.main.model.PopularMovie

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
class PopularMovieAdapter(
    val onItemClicked: (PopularMovie) -> Unit
) : RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    private var list: ArrayList<PopularMovie> = ArrayList()
    private var listFiltered: ArrayList<PopularMovie> = ArrayList()

    fun addData(list: List<PopularMovie>) {
        this.list = list as ArrayList<PopularMovie>
        listFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listFiltered.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                binding.tvTitleMoviePopular.text = title
                binding.tvRated.text = "$vote_average/10 IMDb"
                binding.tvViewer.text = "$popularity Watched"
                binding.ivMovie.loadImage(IMAGE_SOURCE + poster_path)
                binding.itemLayout.setOnClickListener {
                    onItemClicked(this)
                }
            }
        }
    }

    inner class ViewHolder(val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root)
}