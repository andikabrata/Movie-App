package com.example.myapplication.core.di

import com.example.myapplication.common.utils.AppDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coreModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
}