package com.example.jetcomposewithcleanarchitecture.presentation.bike_network_list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkEntity

@Composable
fun BikeNetworkList(
    bikeNetworkList: List<BikeNetworkEntity>,
    onItemClick: (networkId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
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
                text = item.name ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "${item.city}, ${item.country}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
            id = "1",
            name = "New Delhi Bikers Club",
            href = "",
            city = "New Delhi",
            country = "India"
        ),
        onItemClick = {}
    )
}

