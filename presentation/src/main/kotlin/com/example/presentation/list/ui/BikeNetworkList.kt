package com.example.presentation.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.theme.AppTheme
import com.example.domain.entity.BikeNetworkEntity
import com.example.presentation.previewData.FIRST_CITY
import com.example.presentation.previewData.FIRST_COUNTRY
import com.example.presentation.previewData.FIRST_NAME
import com.example.presentation.previewData.NETWORK_ID

@Composable
fun BikeNetworkList(
    bikeNetworkList: List<BikeNetworkEntity>,
    onItemClick: (networkId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = AppTheme.dimens.grid2),
        contentPadding = PaddingValues(vertical = AppTheme.dimens.grid1)
    ) {
        items(
            items = bikeNetworkList,
            key = { item -> item.id }
        ) { item ->
            ListItemRow(item = item, onItemClick = onItemClick)
        }
    }
}

@Composable
fun ListItemRow(
    modifier: Modifier = Modifier,
    item: BikeNetworkEntity,
    onItemClick: (networkId: String) -> Unit
) {
    Surface(
        onClick = { onItemClick(item.id) },
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier.padding(vertical = AppTheme.dimens.grid0p5),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.grid1)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "${item.city}, ${item.country}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview
@Composable
fun ListItemRowPreview() {
    ListItemRow(
        modifier = Modifier,
        item = BikeNetworkEntity(
            id = NETWORK_ID,
            name = FIRST_NAME,
            city = FIRST_CITY,
            country = FIRST_COUNTRY
        ),
        onItemClick = {}
    )
}

