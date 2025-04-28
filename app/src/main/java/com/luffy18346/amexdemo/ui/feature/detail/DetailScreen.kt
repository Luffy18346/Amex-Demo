package com.luffy18346.amexdemo.ui.feature.detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luffy18346.amexdemo.R
import com.luffy18346.amexdemo.domain.model.Picture
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewEvent
import com.luffy18346.amexdemo.ui.base.ScreenContract.ViewSideEffect
import com.luffy18346.amexdemo.ui.utils.getVerticalArrangement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    fileName: String,
    imageUrl: String,
    authorName: String,
    imageWidth: Long,
    imageHeight: Long,
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

                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = fileName,
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
        imageUrl.let { picture ->
            Column(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = getVerticalArrangement(imageWidth, imageHeight)
            ) {
                NetworkImage(picture, fileName)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_16dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = authorName,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun NetworkImage(
    url: String,
    contentDescription: String,
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(url) {
        withContext(Dispatchers.IO) {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.connect()
                val inputStream = connection.inputStream
                val loadedBitmap = BitmapFactory.decodeStream(inputStream)
                bitmap = loadedBitmap
                inputStream.close()
                connection.disconnect()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = contentDescription,
        )
    } ?: Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}