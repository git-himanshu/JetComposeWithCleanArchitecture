package com.example.jetcomposewithcleanarchitecture.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CaptionedRowComposable(caption: String, value: String, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = caption,
            modifier = modifier,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = value,
            modifier = modifier.padding(start = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Preview
@Composable
fun CaptionedRowComposablePreview() {
    Surface {
        CaptionedRowComposable(caption = "Caption:", value = "Value")
    }
}