package com.luffy18346.amexdemo.ui.feature.detail

import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewEvent
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewSideEffect

class DetailContract {

    sealed class Event : ViewEvent {
        object BackButtonClicked : Event()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object Back : Navigation()
        }
    }
}
