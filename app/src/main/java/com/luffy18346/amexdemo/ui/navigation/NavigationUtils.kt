package com.luffy18346.amexdemo.ui.navigation

import androidx.navigation.NavController
import com.luffy18346.amexdemo.ui.navigation.Navigation.Args.PICTURE_ID

object Navigation {

    object Args {
        const val PICTURE_ID = "picture_id"
    }

    object Routes {
        const val PICTURES_PARENT = "pictures_parent"
        const val PICTURES = "pictures"
        const val PICTURE_DETAILS = "$PICTURES/{$PICTURE_ID}"
    }
}


fun NavController.navigateToRepos(pictureId: Long) {
    navigate(route = "${Navigation.Routes.PICTURES}/$pictureId")
}

