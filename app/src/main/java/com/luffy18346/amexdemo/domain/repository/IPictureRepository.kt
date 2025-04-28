package com.luffy18346.amexdemo.domain.repository

import com.luffy18346.amexdemo.domain.model.Picture

interface IPictureRepository {
    suspend fun getPictures(): Result<List<Picture>>
}
