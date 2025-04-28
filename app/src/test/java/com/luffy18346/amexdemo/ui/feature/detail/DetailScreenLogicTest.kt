package com.luffy18346.amexdemo.ui.feature.detail

import androidx.compose.foundation.layout.Arrangement
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.ui.utils.getVerticalArrangement
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DetailScreenLogicTest {
    @Test
    fun `getVerticalArrangement returns Center for landscape images`() {
        val landscapePicture = Picture(
            format = "jpeg",
            width = 1000,
            height = 500,
            author = "author1",
            filename = "1.jpeg",
            id = 1,
            postUrl = "dummy_post_url_1",
            authorUrl = "dummy_author_url_1",
        )

        val result = getVerticalArrangement(landscapePicture.width, landscapePicture.height)
        assertEquals(Arrangement.Center, result)
    }

    @Test
    fun `getVerticalArrangement returns Top for portrait images`() {
        val portraitPicture = Picture(
            format = "jpeg",
            width = 500,
            height = 1000,
            author = "author2",
            filename = "2.jpeg",
            id = 2,
            postUrl = "dummy_post_url_2",
            authorUrl = "dummy_author_url_2",
        )

        val result = getVerticalArrangement(portraitPicture.width, portraitPicture.height)
        assertEquals(Arrangement.Top, result)
    }
}