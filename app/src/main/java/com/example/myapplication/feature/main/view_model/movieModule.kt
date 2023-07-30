package com.example.myapplication.feature.main.view_model

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
val movieModule = module {
    viewModel {
        MovieViewModel(get(), get())
    }
}