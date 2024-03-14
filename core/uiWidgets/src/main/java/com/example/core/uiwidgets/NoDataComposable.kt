package com.example.core.uiwidgets


import android.annotation.SuppressLint
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
import com.example.core.Constants.NO_DATA_TEXT
import com.example.core.Constants.RETRY
import com.example.core.theme.AppTheme
import com.example.core.uiWidgets.R


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
                .padding(AppTheme.dimens.grid3),
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
                Spacer(modifier = Modifier.height(AppTheme.dimens.grid4))
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.dimens.grid2))
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

@SuppressLint("PrivateResource")
@Preview
@Composable
fun NoDataComposablePreview() {
    NoDataComposable(
        infoText = NO_DATA_TEXT,
        onRetry = {},
        retryButtonLabel = RETRY,
        noDataDrawable = R.drawable.no_data
    )
}