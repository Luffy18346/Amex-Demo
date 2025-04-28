package com.luffy18346.amexdemo.data.mapper

import com.luffy18346.amexdemo.data.model.PictureResponse
import com.luffy18346.amexdemo.domain.model.Picture

fun List<PictureResponse>.toPictureList(): List<Picture> = map { it.toPicture() }

fun PictureResponse.toPicture(): Picture {
    return Picture(
        format = this.format,
        width = this.width,
        height = this.height,
        filename = this.filename,
        id = this.id,
        author = this.author,
        authorUrl = this.authorUrl,
        postUrl = this.postUrl
    )
}