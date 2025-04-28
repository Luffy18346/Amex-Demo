package com.luffy18346.amexdemo.data.repository

import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.domain.repository.IPictureRepository

class FakePictureRepositoryImpl : IPictureRepository {

    var shouldReturnError = false
    private var pictures = emptyList<Picture>()

    fun setPictures(pictures: List<Picture>) {
        this.pictures = pictures
    }

    override suspend fun getPictures(): Result<List<Picture>> {
        return if (shouldReturnError) {
            Result.failure(Exception("Fake Error"))
        } else {
            Result.success(pictures)
        }
    }
}