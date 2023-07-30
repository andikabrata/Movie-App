package com.example.myapplication.core.di

import com.example.myapplication.feature.detail_movie.view_model.detailMovieModule
import com.example.myapplication.feature.main.view_model.movieModule


/**
 * List of modules
 */
val appComponent = listOf(
    coreModule,
    retrofitModule,
    movieModule,
    detailMovieModule
)
