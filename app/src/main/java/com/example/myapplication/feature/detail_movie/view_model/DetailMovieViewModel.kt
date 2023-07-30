package com.example.myapplication.feature.detail_movie.view_model

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.BuildConfig
import com.example.myapplication.common.extension.ViewState
import com.example.myapplication.common.extension.setError
import com.example.myapplication.common.extension.setLoading
import com.example.myapplication.common.extension.setSuccess
import com.example.myapplication.common.utils.AppDispatchers
import com.example.myapplication.core.api.ServiceApi
import com.example.myapplication.core.base.BaseViewModel
import com.example.myapplication.feature.detail_movie.model.ActorMovie
import com.example.myapplication.feature.detail_movie.model.BaseResponseActor
import com.example.myapplication.feature.detail_movie.model.DetailMovie
import com.example.myapplication.feature.detail_movie.model.VideoMovie
import com.example.myapplication.feature.main.model.BaseResponse

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
class DetailMovieViewModel(
    private val serviceUtil: ServiceApi,
    private val dispatchers: AppDispatchers,
) : BaseViewModel() {

    val detailMovieLiveData = MutableLiveData<ViewState<DetailMovie>>()
    val actorMovieLiveData = MutableLiveData<ViewState<BaseResponseActor<ActorMovie>>>()
    val videoMovieLiveData = MutableLiveData<ViewState<BaseResponse<VideoMovie>>>()
    val movieId = MutableLiveData(0)

    fun getDetailMovie() {
        detailMovieLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getDetailMovie(movieId = movieId.value, BuildConfig.API_KEY)
                detailMovieLiveData.setSuccess(result)
            },
            onError = {
                detailMovieLiveData.setError(it)
            }
        )
    }


    fun getActorMovie() {
        actorMovieLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getActorMovie(movieId = movieId.value, BuildConfig.API_KEY)
                actorMovieLiveData.setSuccess(result)
            },
            onError = {
                actorMovieLiveData.setError(it)
            }
        )
    }

    fun getVideoMovie() {
        videoMovieLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getVideoMovie(movieId = movieId.value, BuildConfig.API_KEY)
                videoMovieLiveData.setSuccess(result)
            },
            onError = {
                actorMovieLiveData.setError(it)
            }
        )
    }
}