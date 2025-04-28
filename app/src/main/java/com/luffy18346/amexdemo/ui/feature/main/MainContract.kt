package com.luffy18346.amexdemo.ui.feature.main

import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewEvent
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewSideEffect
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewState

class MainContract {
    sealed class Event : ViewEvent {
        data object Retry : Event()
        data class PictureSelection(val picture: Picture) : Event()
    }

    data class State(
        val isLoading: Boolean,
        val isError: Boolean,
        val data: List<Picture>?,
    ) : ViewState {

        companion object {
            fun initial() = State(
                isLoading = true,
                isError = false,
                data = emptyList(),
            )
        }
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToPictureDetail(val picture: Picture) : Navigation()
        }
    }
}
