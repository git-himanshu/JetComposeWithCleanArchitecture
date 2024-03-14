package com.example.core.uiwidgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.Constants.GENERIC_ERROR_MESSAGE
import com.example.core.Constants.RETRY
import com.example.core.theme.AppTheme

@Composable
fun ErrorComposable(
        errorText: String,
        modifier: Modifier = Modifier,
        retryButtonLabel: String? = null,
        onRetry: (() -> Unit)? = null,
) {
    Surface(color = MaterialTheme.colorScheme.errorContainer) {
        Column(
                modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(AppTheme.dimens.grid3),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
            Text(
                    textAlign = TextAlign.Center,
                    text = errorText,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer,
            )
            retryButtonLabel?.let {
                onRetry?.let {
                    Spacer(modifier = Modifier.height(AppTheme.dimens.grid2))
                    ElevatedButton(onClick = it) {
                        Text(text = retryButtonLabel)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ErrorComposablePreview() {
    ErrorComposable(
            GENERIC_ERROR_MESSAGE,
            onRetry = {},
            retryButtonLabel = RETRY
    )
}