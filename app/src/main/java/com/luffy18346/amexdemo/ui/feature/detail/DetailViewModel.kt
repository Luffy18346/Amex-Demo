package com.luffy18346.amexdemo.ui.feature.detail

import com.luffy18346.amexdemo.ui.base.BaseViewModel

class DetailViewModel() : BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {

    override fun setInitialState() = DetailContract.State.initial()

    override fun handleEvents(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.BackButtonClicked -> setEffect { DetailContract.Effect.Navigation.Back }
        }
    }
}