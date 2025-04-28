package com.luffy18346.amexdemo.data

import com.luffy18346.amexdemo.data.model.PictureResponse
import retrofit2.http.GET

interface PicsumPhotosApi {
    @GET("list")
    suspend fun getPictures(): List<PictureResponse>
}
