package com.luffy18346.amexdemo.ui.navigation

import androidx.navigation.NavController
import com.luffy18346.amexdemo.domain.model.Picture
import kotlinx.serialization.Serializable

sealed class NavigationRoutes {
    @Serializable
    data object Pictures : NavigationRoutes()

    @Serializable
    data class PictureDetails(
        val authorName: String,
        val fileName: String,
        val imageWidth: Long,
        val imageHeight: Long,
        val imageUrl: String,
    ) : NavigationRoutes()
}

// SharedViewModel or Store the list in singleton or repository and pass the id and
// then fetch the detail object in detail screen
fun NavController.navigateToRepos(picture: Picture) {
    navigate(
        route = NavigationRoutes.PictureDetails(
            authorName = picture.author,
            fileName = picture.filename,
            imageWidth = picture.width,
            imageHeight = picture.height,
            imageUrl = picture.getImageUrl(),
        )
    )
}