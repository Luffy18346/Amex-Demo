package com.luffy18346.amexdemo.data.repository

import com.luffy18346.amexdemo.data.PicsumPhotosApi
import com.luffy18346.amexdemo.data.mapper.toPictureList
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.domain.repository.IPictureRepository

class PictureRepositoryImpl(
    private val picsumPhotosApi: PicsumPhotosApi,
) : IPictureRepository {

    override suspend fun getPictures(): Result<List<Picture>> = makeApiCall {
        picsumPhotosApi.getPictures().toPictureList()
    }
}

suspend fun <T> makeApiCall(
    call: suspend () -> T
): Result<T> = runCatching {
    call.invoke()
}
