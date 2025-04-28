package com.luffy18346.amexdemo.ui.feature.main

import androidx.lifecycle.viewModelScope
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.ui.base.BaseViewModel
import com.luffy18346.amexdemo.domain.use_case.GetPicturesUseCase
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewEvent
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewSideEffect
import com.luffy18346.amexdemo.ui.feature.detail.DetailContract
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPictures: GetPicturesUseCase,
) : BaseViewModel<ViewEvent, MainContract.State, ViewSideEffect>() {

    init {
        getPictures()
    }

    override fun setInitialState() = MainContract.State.initial()

    override fun handleEvents(event: ViewEvent) {
        when (event) {
            is MainContract.Event.PictureSelection -> setEffect {
                MainContract.Effect.Navigation.ToPictureDetail(
                    event.picture.id
                )
            }

            is MainContract.Event.Retry -> getPictures()
            is DetailContract.Event.BackButtonClicked -> setEffect { DetailContract.Effect.Navigation.Back }
        }
    }

    fun getPictures() {
        setState { copy(isLoading = true, isError = false) }
        viewModelScope.launch {
            getPictures.invoke().onSuccess { it ->
                setState { copy(data = it, isLoading = false) }
            }.onFailure {
                setState { copy(isError = true, isLoading = false) }
            }
        }
    }

    fun getPictureById(pictureId: Long): Picture? {
        return viewState.value.data?.find { it.id == pictureId }
    }
}