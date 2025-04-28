package com.luffy18346.amexdemo.ui.utils

import androidx.compose.foundation.layout.Arrangement
import com.luffy18346.amexdemo.domain.model.Picture

// Isolate vertical arrangement logic for landscape or portrait images
fun getVerticalArrangement(imageWidth: Long, imageHeight: Long,): Arrangement.Vertical {
    return if (imageWidth > imageHeight) Arrangement.Center else Arrangement.Top
}