package com.luffy18346.amexdemo.domain.model

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
    fun getImageUrl() = "https://picsum.photos/$width/$height?image=$id"
}
