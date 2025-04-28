package com.luffy18346.amexdemo.domain.model

import com.luffy18346.amexdemo.data.model.PictureResponse

data class Picture(
    val format: String,
    val width: Long,
    val height: Long,
    val filename: String,
    val id: Long,
    val author: String,
    val authorUrl: String,
    val postUrl: String,
) {
    fun getImageUrl(): String {
        return "https://picsum.photos/$width/$height?image=$id"
    }
}

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