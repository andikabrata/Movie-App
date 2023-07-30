package com.example.myapplication.feature.detail_movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.common.extension.loadImage
import com.example.myapplication.core.di.IMAGE_SOURCE
import com.example.myapplication.databinding.ItemActorMovieBinding
import com.example.myapplication.feature.detail_movie.model.ActorMovie

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
class ActorAdapter(
    val onItemClicked: (ActorMovie) -> Unit
) : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    private var list: ArrayList<ActorMovie> = ArrayList()
    private var listFiltered: ArrayList<ActorMovie> = ArrayList()

    fun addData(list: List<ActorMovie>) {
        this.list = list as ArrayList<ActorMovie>
        listFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemActorMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                binding.ivActor.loadImage(IMAGE_SOURCE + profile_path)
                binding.tvNameActor.text = original_name
            }
        }
    }

    inner class ViewHolder(val binding: ItemActorMovieBinding) : RecyclerView.ViewHolder(binding.root)
}