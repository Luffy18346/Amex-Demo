package com.luffy18346.amexdemo.ui.feature.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.luffy18346.amexdemo.R
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewEvent
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewSideEffect
import com.luffy18346.amexdemo.ui.feature.common.NetworkError
import com.luffy18346.amexdemo.ui.feature.common.Progress
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainContract.State,
    effectFlow: Flow<MainContract.Effect>,
    onEventSent: (event: MainContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: MainContract.Effect.Navigation) -> Unit,
) {

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is MainContract.Effect.Navigation.ToPictureDetail -> {
                    onNavigationRequested(effect)
                }

                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.main_screen_title),
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                )
            )
        },
    ) {
        when {
            state.isLoading -> Progress()
            state.isError -> NetworkError { onEventSent(MainContract.Event.Retry) }
            else -> MainScreenContent(
                pictures = state.data ?: emptyList(),
                paddingValues = it
            ) { onEventSent(MainContract.Event.PictureSelection(it)) }
        }
    }
}

@Composable
private fun MainScreenContent(
    pictures: List<Picture>,
    paddingValues: PaddingValues,
    onItemClick: (Picture) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(vertical = dimensionResource(R.dimen.padding_8dp))
    ) {
        itemsIndexed(pictures) { index, item ->
            PictureListItem(item = item, onItemClick)
        }
    }
}

@Composable
private fun PictureListItem(
    item: Picture,
    onItemClick: (Picture) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.padding_16dp),
                vertical = dimensionResource(R.dimen.padding_8dp),
            ),
        onClick = {
            onItemClick.invoke(item)
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_16dp)),
        ) {
            Text(
                text = item.filename,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(
                    R.string.main_screen_picture_item_dimensions,
                    item.width,
                    item.height
                ),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.secondary,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

