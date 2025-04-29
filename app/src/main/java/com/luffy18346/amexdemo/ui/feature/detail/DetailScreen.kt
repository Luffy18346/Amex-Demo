package com.luffy18346.amexdemo.ui.feature.detail

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.luffy18346.amexdemo.R
import com.luffy18346.amexdemo.ui.feature.common.NetworkError
import com.luffy18346.amexdemo.ui.feature.common.Progress
import com.luffy18346.amexdemo.ui.navigation.NavigationRoutes.PictureDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailContract.State,
    effectFlow: Flow<DetailContract.Effect>,
    onEventSent: (event: DetailContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: DetailContract.Effect.Navigation) -> Unit,
) {

    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is DetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(effect)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.pictureDetails?.fileName ?: "",
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        onEventSent(DetailContract.Event.BackButtonClicked)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        when {
            state.isLoading -> Progress()
            state.isError -> NetworkError { onEventSent(DetailContract.Event.Retry) }
            else -> DetailScreenContent(
                pictureDetails = state.pictureDetails,
                image = state.image,
                verticalArrangement = state.verticalArrangement,
                paddingValues = it
            )
        }

    }
}

@Composable
fun DetailScreenContent(
    pictureDetails: PictureDetails?,
    image: Bitmap?,
    verticalArrangement: Arrangement.Vertical,
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = verticalArrangement
    ) {
        image?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = pictureDetails?.fileName ?: "",
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_16dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = pictureDetails?.authorName ?: "",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}