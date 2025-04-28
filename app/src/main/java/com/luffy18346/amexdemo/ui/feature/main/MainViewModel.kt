package com.luffy18346.amexdemo.ui.feature.main

import androidx.lifecycle.viewModelScope
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.ui.base.BaseViewModel
import com.luffy18346.amexdemo.domain.use_case.GetPicturesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPictures: GetPicturesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    init {
        getPictures()
    }

    override fun setInitialState() = MainContract.State.initial()

    override fun handleEvents(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.PictureSelection -> setEffect {
                MainContract.Effect.Navigation.ToPictureDetail(
                    event.picture
                )
            }

            is MainContract.Event.Retry -> getPictures()
        }
    }

    fun getPictures() {
        setState { copy(isLoading = true, isError = false) }
        viewModelScope.launch(ioDispatcher) {
            getPictures.invoke().onSuccess {
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