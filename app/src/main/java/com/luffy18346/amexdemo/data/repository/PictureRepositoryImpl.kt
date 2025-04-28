package com.luffy18346.amexdemo.data.repository

import com.luffy18346.amexdemo.data.PicsumPhotosApi
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.domain.model.toPicture
import com.luffy18346.amexdemo.domain.repository.IPictureRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PictureRepositoryImpl(
    private val picsumPhotosApi: PicsumPhotosApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPictureRepository {

    override suspend fun getPictures(): Result<List<Picture>> = makeApiCall(dispatcher) {
        picsumPhotosApi.getPictures().map { it.toPicture() }
    }
}

suspend fun <T> makeApiCall(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> T
): Result<T> = runCatching {
    withContext(dispatcher) {
        call.invoke()
    }
}
