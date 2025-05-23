package com.luffy18346.amexdemo.ui.feature.detail

import android.graphics.BitmapFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.luffy18346.amexdemo.ui.base.BaseViewModel
import com.luffy18346.amexdemo.ui.feature.detail.DetailContract.Effect
import com.luffy18346.amexdemo.ui.feature.detail.DetailContract.Event
import com.luffy18346.amexdemo.ui.feature.detail.DetailContract.State
import com.luffy18346.amexdemo.ui.navigation.NavigationRoutes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel<Event, State, Effect>() {

    private val _pictureDetails = savedStateHandle.toRoute<NavigationRoutes.PictureDetails>()

    init {
        loadImage()
    }

    override fun setInitialState() = State.initial()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.BackButtonClicked -> setEffect { Effect.Navigation.Back }
            is Event.Retry -> loadImage()
        }
    }

    private fun loadImage() {
        setState { copy(isLoading = true, isError = false, pictureDetails = this@DetailViewModel._pictureDetails) }
        viewModelScope.launch(ioDispatcher) {
            try {
                val connection = URL(viewState.value.pictureDetails?.imageUrl).openConnection() as HttpURLConnection
                connection.connect()
                val inputStream = connection.inputStream
                val loadedBitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                connection.disconnect()
                setState { copy(image = loadedBitmap, isLoading = false) }
            } catch (e: IOException) {
                e.printStackTrace()
                setState { copy(isError = true, isLoading = false) }
            }
        }
    }
}