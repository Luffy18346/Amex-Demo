package com.luffy18346.amexdemo.ui.feature.detail

import androidx.compose.foundation.layout.Arrangement
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.ui.navigation.NavigationRoutes
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DetailScreenLogicTest {
    @Test
    fun `getVerticalArrangement returns Center for landscape images`() {
        val landscapePicture = NavigationRoutes.PictureDetails(
            imageWidth = 1000,
            imageHeight = 500,
            authorName = "author1",
            fileName = "1.jpeg",
            imageUrl = "dummy_post_url_1",
        )
        val result = DetailContract.State(
            isError = false,
            isLoading = true,
            image = null,
            pictureDetails = landscapePicture
        )
        assertEquals(Arrangement.Center, result.verticalArrangement)
    }

    @Test
    fun `getVerticalArrangement returns Top for portrait images`() {
        val portraitPicture = NavigationRoutes.PictureDetails(
            imageWidth = 500,
            imageHeight = 1000,
            authorName = "author2",
            fileName = "2.jpeg",
            imageUrl = "dummy_post_url_2",
        )
        val result = DetailContract.State(
            isError = false,
            isLoading = true,
            image = null,
            pictureDetails = portraitPicture
        )
        assertEquals(Arrangement.Top, result.verticalArrangement)
    }

    @Test
    fun `getVerticalArrangement returns Center when width equals height`() {
        val squarePicture = NavigationRoutes.PictureDetails(
            imageWidth = 800,
            imageHeight = 800,
            authorName = "author3",
            fileName = "3.jpeg",
            imageUrl = "dummy_post_url_3",
        )
        val result = DetailContract.State(
            isError = false,
            isLoading = true,
            image = null,
            pictureDetails = squarePicture
        )
        assertEquals(Arrangement.Center, result.verticalArrangement)
    }
}