package com.example.myapplication.feature.main.model

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
data class BaseResponse<T>(
    val page: String, val results: List<T>?
)