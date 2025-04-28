package com.luffy18346.amexdemo.ui.navigation

import androidx.navigation.NavController
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.AUTHOR_NAME
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.FILE_NAME
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.IMAGE_HEIGHT
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.IMAGE_URL
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.IMAGE_WIDTH

object Navigation {

    object Args {
        const val AUTHOR_NAME = "author_name"
        const val FILE_NAME = "file_name"
        const val IMAGE_WIDTH = "image_width"
        const val IMAGE_HEIGHT = "image_height"
        const val IMAGE_URL = "image_url"
    }

    object Routes {  // Work on this...
        const val PICTURES = "pictures"
        const val PICTURE_DETAILS =
            "$PICTURES/{$AUTHOR_NAME}/{$IMAGE_URL}/{$FILE_NAME}/{$IMAGE_WIDTH}/{$IMAGE_HEIGHT}"
    }
}


fun NavController.navigateToRepos(picture: Picture) {
    navigate(
        route =
            "${Navigation.Routes.PICTURES}/${picture.author}/${picture.getImageUrl()}/${picture.filename}/${picture.width}/${picture.height}"
    )
}

// Data Domain - dependency
// DI
// Dont share a common viewModel.... ===
// Navigation