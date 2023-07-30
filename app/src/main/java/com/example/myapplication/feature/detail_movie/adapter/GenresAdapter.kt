package com.example.myapplication.feature.detail_movie.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemGenresBinding
import com.example.myapplication.feature.detail_movie.model.GendresModel

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
class GenresAdapter(
    val onItemClicked: (GendresModel) -> Unit
) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    private var list: ArrayList<GendresModel> = ArrayList()
    private var listFiltered: ArrayList<GendresModel> = ArrayList()

    fun addData(list: List<GendresModel>) {
        this.list = list as ArrayList<GendresModel>
        listFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenresBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                binding.tvGendres.text = name
            }
        }
    }

    inner class ViewHolder(val binding: ItemGenresBinding) : RecyclerView.ViewHolder(binding.root)
}