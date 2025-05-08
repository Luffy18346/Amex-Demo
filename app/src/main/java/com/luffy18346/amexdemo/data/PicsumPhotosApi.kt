package com.luffy18346.amexdemo.data

import com.luffy18346.amexdemo.data.model.PictureResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface PicsumPhotosApi {
    @GET("list")
    suspend fun getPictures(): List<PictureResponse>

    // Different urls for some apis...
//    @GET
//    suspend fun getPictures1(@Url url: Url): List<PictureResponse>
}
