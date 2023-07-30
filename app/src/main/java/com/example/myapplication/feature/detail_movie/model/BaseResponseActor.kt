package com.example.myapplication.feature.detail_movie.model

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
data class BaseResponseActor<T>(
    val id: Int, val cast: List<T>?
)