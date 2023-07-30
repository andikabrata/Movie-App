package com.example.myapplication.core.api

import com.example.myapplication.feature.detail_movie.model.ActorMovie
import com.example.myapplication.feature.detail_movie.model.BaseResponseActor
import com.example.myapplication.feature.detail_movie.model.DetailMovie
import com.example.myapplication.feature.detail_movie.model.VideoMovie
import com.example.myapplication.feature.main.model.BaseResponse
import com.example.myapplication.feature.main.model.NowPlayingMovie
import com.example.myapplication.feature.main.model.PopularMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Andika Bratadirja
 * @date 29/07/2023
 */
interface ServiceApi {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String?
    ): BaseResponse<NowPlayingMovie>

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String?
    ): BaseResponse<PopularMovie>

    @GET("movie/{movieId}")
    suspend fun getDetailMovie(
        @Path("movieId") movieId: Int?,
        @Query("api_key") apiKey: String?
    ): DetailMovie

    @GET("movie/{movieId}/credits")
    suspend fun getActorMovie(
        @Path("movieId") movieId: Int?,
        @Query("api_key") apiKey: String?
    ): BaseResponseActor<ActorMovie>

    @GET("movie/{movieId}/videos")
    suspend fun getVideoMovie(
        @Path("movieId") movieId: Int?,
        @Query("api_key") apiKey: String?
    ): BaseResponse<VideoMovie>
}