package com.example.myapplication.common.utils

import kotlinx.coroutines.CoroutineDispatcher


class AppDispatchers(val main: CoroutineDispatcher,
                     val io: CoroutineDispatcher)