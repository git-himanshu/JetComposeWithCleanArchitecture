package com.example.core.uiwidgets

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.theme.AppTheme


@Composable
fun NoDataComposable(
    errorText: String,
    noDataDrawable: Int,
    retryButtonLabel: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    Surface(color = MaterialTheme.colorScheme.tertiaryContainer) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painterResource(noDataDrawable),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.grid_4))
            Text(text = errorText, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(AppTheme.dimens.grid_2))
            ElevatedButton(
                onClick = onRetry, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer
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
        "No Data Found",
        onRetry = {},
        retryButtonLabel = "Retry",
        noDataDrawable = R.drawable.stat_sys_data_bluetooth
    )
}