package com.example.myapplication.feature.main.view_model

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.BuildConfig
import com.example.myapplication.common.extension.ViewState
import com.example.myapplication.common.extension.setError
import com.example.myapplication.common.extension.setLoading
import com.example.myapplication.common.extension.setSuccess
import com.example.myapplication.common.utils.AppDispatchers
import com.example.myapplication.core.api.ServiceApi
import com.example.myapplication.core.base.BaseViewModel
import com.example.myapplication.feature.main.model.BaseResponse
import com.example.myapplication.feature.main.model.NowPlayingMovie
import com.example.myapplication.feature.main.model.PopularMovie

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
class MovieViewModel(
    private val serviceUtil: ServiceApi,
    private val dispatchers: AppDispatchers,
) : BaseViewModel() {

    val listNowPlayinMovieLiveData = MutableLiveData<ViewState<BaseResponse<NowPlayingMovie>>>()
    val listPopularMovieLiveData = MutableLiveData<ViewState<BaseResponse<PopularMovie>>>()

    fun getListNowPlayingMovie() {
        listNowPlayinMovieLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getNowPlayingMovie(BuildConfig.API_KEY)
                listNowPlayinMovieLiveData.setSuccess(result)
            },
            onError = {
                listNowPlayinMovieLiveData.setError(it)
            }
        )
    }

    fun getListPopularMovie() {
        listPopularMovieLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getPopularMovie(BuildConfig.API_KEY)
                listPopularMovieLiveData.setSuccess(result)
            },
            onError = {
                listPopularMovieLiveData.setError(it)
            }
        )
    }
}