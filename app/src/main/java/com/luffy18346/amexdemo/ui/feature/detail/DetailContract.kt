package com.luffy18346.amexdemo.ui.feature.detail

import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewEvent
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewSideEffect
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewState

class DetailContract {

    sealed class Event : ViewEvent {
        data object BackButtonClicked : Event()
    }

    class State() : ViewState {

        companion object {
            fun initial() = State()
        }
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }
}
