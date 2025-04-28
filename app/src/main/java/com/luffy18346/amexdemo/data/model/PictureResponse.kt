package com.luffy18346.amexdemo.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureResponse(
    @Json(name = "format")
    val format: String,

    @Json(name = "width")
    val width: Long,

    @Json(name = "height")
    val height: Long,

    @Json(name = "filename")
    val filename: String,

    @Json(name = "id")
    val id: Long,

    @Json(name = "author")
    val author: String,

    @Json(name = "author_url")
    val authorUrl: String,

    @Json(name = "post_url")
    val postUrl: String,
)
