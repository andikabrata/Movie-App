package com.example.myapplication.feature.main.model

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
data class PopularMovie(
    val id: Int,
    val popularity: Float,
    val title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String,
    val vote_average: String
)