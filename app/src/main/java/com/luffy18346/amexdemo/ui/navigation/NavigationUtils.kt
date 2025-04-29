package com.luffy18346.amexdemo.ui.navigation

import androidx.navigation.NavController
import com.luffy18346.amexdemo.domain.model.Picture
import kotlinx.serialization.Serializable
import okhttp3.Route

sealed class NavigationRoutes {  // Work on this...
    @Serializable
    object Pictures : NavigationRoutes()

    @Serializable
    data class PictureDetails(
        val authorName: String,
        val fileName: String,
        val imageWidth: Long,
        val imageHeight: Long,
        val imageUrl: String,
    ) : NavigationRoutes()
}


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

// Data Domain - dependency
// DI
// Dont share a common viewModel.... ===
// Navigation