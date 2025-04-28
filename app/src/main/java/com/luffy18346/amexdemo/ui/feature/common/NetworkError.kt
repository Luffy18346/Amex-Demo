package com.luffy18346.amexdemo.ui.feature.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.luffy18346.amexdemo.R

@Composable
fun NetworkError(
    modifier: Modifier = Modifier,
    onRetryButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.network_error_title),
            style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.primary),
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(R.string.network_error_description),
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_16dp)),
            textAlign = TextAlign.Center,
        )
        Button(onClick = { onRetryButtonClick() }) {
            Text(
                text = stringResource(R.string.network_error_retry_button_text).uppercase()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkErrorPreview() {
    NetworkError(modifier = Modifier, onRetryButtonClick = {})
}