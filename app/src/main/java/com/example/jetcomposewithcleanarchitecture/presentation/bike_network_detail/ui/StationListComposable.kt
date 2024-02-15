package com.example.jetcomposewithcleanarchitecture.presentation.bike_network_detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetcomposewithcleanarchitecture.R
import com.example.jetcomposewithcleanarchitecture.domain.entity.Station
import com.example.jetcomposewithcleanarchitecture.presentation.composables.CaptionedRowComposable

@Composable
fun StationListComposable(modifier: Modifier = Modifier, stationList: List<Station>) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 20.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = stationList,
            key = { item -> item.id }
        ) { item ->
            StationListRow(station = item)
        }
    }
}

@Composable
fun StationListRow(station: Station, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.padding(vertical = 4.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = station.name ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(12.dp))
            CaptionedRowComposable(
                caption = stringResource(id = R.string.total_slots),
                value = station.slots?.toString() ?: "",
                modifier = Modifier.padding(vertical = 1.dp)
            )
            CaptionedRowComposable(
                caption = stringResource(id = R.string.available_slots),
                value = station.emptySlot?.toString() ?: "",
                modifier = Modifier.padding(vertical = 1.dp)
            )
            CaptionedRowComposable(
                caption = stringResource(id = R.string.free_bikes),
                value = station.freeBikes?.toString() ?: "",
                modifier = Modifier.padding(vertical = 1.dp)
            )

        }
    }
}

@Preview
@Composable
fun StationListRowPreview() {
    StationListRow(
        Station(
            id = "network id",
            freeBikes = 10,
            name = "Network Name",
            address = "Address of network",
            emptySlot = 14,
            slots = 20,
        )
    )
}