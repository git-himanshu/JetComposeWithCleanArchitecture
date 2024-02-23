package com.example.core.uiwidgets

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.theme.AppTheme


@Composable
fun NoDataComposable(
    modifier: Modifier = Modifier,
    iconContentDescription: String = "",
    infoText: String? = null,
    noDataDrawable: Int? = null,
    retryButtonLabel: String,
    onRetry: () -> Unit,
) {
    Surface(color = MaterialTheme.colorScheme.secondaryContainer) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(AppTheme.dimens.grid_3),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            noDataDrawable?.let {
                Icon(
                    painterResource(it),
                    contentDescription = iconContentDescription
                )
            }
            infoText?.let {
                Spacer(modifier = Modifier.height(AppTheme.dimens.grid_4))
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.dimens.grid_2))
            ElevatedButton(
                onClick = onRetry, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                Text(text = retryButtonLabel)
            }
        }
    }
}

@Preview
@Composable
fun NoDataComposablePreview() {
    NoDataComposable(
        infoText = "No Data Found at the moment, please try again.",
        onRetry = {},
        retryButtonLabel = "Retry",
        noDataDrawable = R.drawable.stat_sys_data_bluetooth
    )
}