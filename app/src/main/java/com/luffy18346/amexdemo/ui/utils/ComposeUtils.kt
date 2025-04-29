package com.luffy18346.amexdemo.ui.utils

import androidx.compose.foundation.layout.Arrangement

// Isolate vertical arrangement logic for landscape or portrait images for testing purpose..
fun getVerticalArrangement(imageWidth: Long, imageHeight: Long,): Arrangement.Vertical {
    return if (imageWidth > imageHeight) Arrangement.Center else Arrangement.Top
}