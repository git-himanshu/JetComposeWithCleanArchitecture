package com.example.feature.bikenetwork.presentation.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bikenetwork.domain.entity.Station
import com.example.core.theme.AppTheme
import com.example.core.uiwidgets.CaptionedRowComposable
import com.example.feature.bikenetwork.presentation.R

@Composable
fun StationListComposable(modifier: Modifier = Modifier, stationList: List<Station>) {
    LazyColumn(
        modifier = modifier.padding(horizontal = AppTheme.dimens.grid_2_5),
        contentPadding = PaddingValues(vertical = AppTheme.dimens.grid_1)
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
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier.padding(vertical = AppTheme.dimens.grid_0_5),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.grid_1)
        ) {
            Text(
                text = station.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(Modifier.height(AppTheme.dimens.grid_1_5))
            CaptionedRowComposable(
                caption = stringResource(id = R.string.total_slots),
                value = station.slots.toString(),
                modifier = Modifier.padding(vertical = AppTheme.dimens.grid_0_25)
            )
            CaptionedRowComposable(
                caption = stringResource(id = R.string.available_slots),
                value = station.emptySlot.toString(),
                modifier = Modifier.padding(vertical = AppTheme.dimens.grid_0_25)
            )
            CaptionedRowComposable(
                caption = stringResource(id = R.string.free_bikes),
                value = station.freeBikes.toString(),
                modifier = Modifier.padding(vertical = AppTheme.dimens.grid_0_25)
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