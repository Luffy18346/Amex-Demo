package com.luffy18346.amexdemo.ui.utils

import androidx.compose.foundation.layout.Arrangement
import com.luffy18346.amexdemo.domain.model.Picture

// Isolate vertical arrangement logic for landscape or portrait images
fun getVerticalArrangement(picture: Picture): Arrangement.Vertical {
    return if (picture.width > picture.height) Arrangement.Center else Arrangement.Top
}