package com.example.myapplication.feature.main.model

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
data class NowPlayingMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String,
    val vote_average: String
)