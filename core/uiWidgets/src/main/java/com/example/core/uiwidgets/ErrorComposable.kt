package com.example.core.uiwidgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.theme.AppTheme

@Composable
fun ErrorComposable(
    errorText: String,
    retryButtonLabel: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    Surface(color = MaterialTheme.colorScheme.errorContainer) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = errorText,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.grid_2))
            ElevatedButton(onClick = onRetry) {
                Text(text = retryButtonLabel)
            }
        }
    }
}

@Preview
@Composable
fun ErrorComposablePreview() {
    ErrorComposable("Something went wrong", onRetry = {}, retryButtonLabel = "Retry")
}