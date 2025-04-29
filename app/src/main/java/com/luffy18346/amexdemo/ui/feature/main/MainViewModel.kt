package com.luffy18346.amexdemo.ui.feature.main

import androidx.lifecycle.viewModelScope
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.domain.use_case.GetPicturesUseCase
import com.luffy18346.amexdemo.ui.base.BaseViewModel
import com.luffy18346.amexdemo.ui.feature.main.MainContract.Effect
import com.luffy18346.amexdemo.ui.feature.main.MainContract.Event
import com.luffy18346.amexdemo.ui.feature.main.MainContract.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPictures: GetPicturesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<Event, State, Effect>() {

    init {
        getPictures()
    }

    override fun setInitialState() = State.initial()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.PictureSelection -> setEffect {
                Effect.Navigation.ToPictureDetail(
                    event.picture
                )
            }

            is Event.Retry -> getPictures()
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