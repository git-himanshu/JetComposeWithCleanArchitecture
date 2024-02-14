package com.example.jetcomposewithcleanarchitecture.presentation.bike_network_list.ui

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkEntity

@Composable
fun BikeNetworkList(
    bikeNetworkList: List<BikeNetworkEntity>,
    onItemClick: (networkId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = bikeNetworkList,
            key = { item -> item.id }
        ) { item ->
            ItemRow(item = item, onItemClick = onItemClick)
        }
    }
}

@Composable
fun ItemRow(
    modifier: Modifier = Modifier,
    item: BikeNetworkEntity,
    onItemClick: (networkId: String) -> Unit
) {
    Surface(
        onClick = { onItemClick(item.id) },
        color = MaterialTheme.colorScheme.inversePrimary,
        modifier = modifier.padding(vertical = 1.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = item.name ?: "", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "${item.city}, ${item.country}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun ItemRowPreview() {
    ItemRow(
        modifier = Modifier,
        item = BikeNetworkEntity(
            id = "1",
            name = "Himanshu",
            href = "",
            city = "Rampur",
            country = "India"
        ),
        onItemClick = {}
    )
}

