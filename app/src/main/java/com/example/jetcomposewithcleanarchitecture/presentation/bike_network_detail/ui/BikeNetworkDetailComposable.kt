package com.example.jetcomposewithcleanarchitecture.presentation.bike_network_detail.ui

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
import androidx.compose.ui.unit.dp
import com.example.jetcomposewithcleanarchitecture.R
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkDetailEntity
import com.example.jetcomposewithcleanarchitecture.domain.entity.Station
import com.example.jetcomposewithcleanarchitecture.presentation.composables.CaptionedRowComposable

@Composable
fun BikeNetworkDetailComposable(modifier: Modifier, bikeNetworkDetail: BikeNetworkDetailEntity?) {

    Column(modifier = modifier) {
        Surface(color = MaterialTheme.colorScheme.primaryContainer) {
            Column(modifier = Modifier.padding(20.dp)) {
                CaptionedRowComposable(
                    caption = stringResource(id = R.string.network_name),
                    value = bikeNetworkDetail?.name ?: "",
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                CaptionedRowComposable(
                    caption = stringResource(id = R.string.location),
                    value = "${bikeNetworkDetail?.city ?: ""}, ${bikeNetworkDetail?.country ?: ""}",
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.all_stations),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleLarge
        )
        StationListComposable(stationList = bikeNetworkDetail?.stations ?: emptyList())
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