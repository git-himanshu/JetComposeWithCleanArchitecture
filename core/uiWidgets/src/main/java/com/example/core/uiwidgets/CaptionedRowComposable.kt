package com.example.core.uiwidgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.Constants.captionLabel
import com.example.core.Constants.value
import com.example.core.theme.AppTheme

@Composable
fun CaptionedRowComposable(caption: String, value: String, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = caption,
            modifier = modifier,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = value,
            modifier = modifier.padding(start = AppTheme.dimens.grid0p5),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Preview
@Composable
fun CaptionedRowComposablePreview() {
    Surface {
        CaptionedRowComposable(caption = captionLabel, value = value)
    }
}