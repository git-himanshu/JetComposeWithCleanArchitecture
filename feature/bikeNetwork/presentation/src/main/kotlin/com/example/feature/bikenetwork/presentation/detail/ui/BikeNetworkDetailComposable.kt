package com.example.feature.bikenetwork.presentation.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.entity.Station
import com.example.core.theme.AppTheme
import com.example.core.uiwidgets.CaptionedRowComposable
import com.example.feature.bikenetwork.presentation.R

@Composable
fun BikeNetworkDetailComposable(
    modifier: Modifier = Modifier,
    bikeNetworkDetail: BikeNetworkDetailEntity
) {

    Column(modifier = modifier) {
        Surface(color = MaterialTheme.colorScheme.primaryContainer) {
            Column(modifier = Modifier.padding(AppTheme.dimens.grid_2_5)) {
                CaptionedRowComposable(
                    caption = stringResource(id = R.string.network_name),
                    value = bikeNetworkDetail.name,
                    modifier = Modifier.padding(vertical = AppTheme.dimens.grid_0_25)
                )
                CaptionedRowComposable(
                    caption = stringResource(id = R.string.location),
                    value = "${bikeNetworkDetail.city}, ${bikeNetworkDetail.country}",
                    modifier = Modifier.padding(vertical = AppTheme.dimens.grid_0_25)
                )
            }
        }
        Spacer(modifier = Modifier.height(AppTheme.dimens.grid_0_5))
        Text(
            text = stringResource(id = R.string.all_stations),
            modifier = Modifier.padding(
                horizontal = AppTheme.dimens.grid_2_5,
                vertical = AppTheme.dimens.grid_1
            ),
            style = MaterialTheme.typography.titleLarge
        )
        StationListComposable(stationList = bikeNetworkDetail.stations)
    }

}

@Preview
@Composable
fun BikeNetworkDetailComposablePreview() {
    Surface {
        BikeNetworkDetailComposable(
            bikeNetworkDetail = BikeNetworkDetailEntity(
                id = "network id",
                name = "Network name",
                href = "",
                city = "New Delhi",
                country = "India",
                stations = listOf<Station>(
                    Station(
                        id = "network id",
                        freeBikes = 10,
                        name = "Network Name",
                        address = "Address of network",
                        emptySlot = 14,
                        slots = 20,
                    )
                )
            ), modifier = Modifier
        )
    }

}