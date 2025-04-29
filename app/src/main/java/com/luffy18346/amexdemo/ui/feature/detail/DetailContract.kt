package com.luffy18346.amexdemo.ui.feature.detail

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewEvent
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewSideEffect
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewState
import com.luffy18346.amexdemo.ui.navigation.NavigationRoutes

class DetailContract {

    sealed class Event : ViewEvent {
        data object Retry : Event()
        data object BackButtonClicked : Event()
    }

    data class State(
        val isLoading: Boolean,
        val isError: Boolean,
        val image: Bitmap?,
        val pictureDetails: NavigationRoutes.PictureDetails?,
    ) : ViewState {

        companion object {
            fun initial() = State(
                isLoading = true,
                isError = false,
                image = null,
                pictureDetails = null
            )
        }

        val verticalArrangement: Arrangement.Vertical
            get() = if ((pictureDetails?.imageWidth ?: 0) >= (pictureDetails?.imageHeight ?: 0)) {
                Arrangement.Center
            } else {
                Arrangement.Top
            }
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }
}
