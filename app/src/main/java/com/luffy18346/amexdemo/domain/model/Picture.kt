package com.luffy18346.amexdemo.domain.model

import android.net.Uri

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
        return Uri.encode("https://picsum.photos/$width/$height?image=$id")
    }
}
