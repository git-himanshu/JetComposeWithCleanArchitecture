package com.example.core.uiwidgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.theme.AppTheme

@Composable
fun LoaderComposable(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(AppTheme.dimens.grid_6),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.inversePrimary,
        )
    }
}

@Preview
@Composable
fun LoaderComposablePreview() {
    LoaderComposable(Modifier)
}