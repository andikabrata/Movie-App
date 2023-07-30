package com.example.myapplication.feature.detail_movie.model

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
data class DetailMovie(
    val id: Int,
    val popularity: Double,
    val genres: List<GendresModel>,
    val title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String,
    val vote_average: Double,
    val runtime: Int,
    val original_language: String
)

data class GendresModel(
    val id: Int,
    val name: String
)