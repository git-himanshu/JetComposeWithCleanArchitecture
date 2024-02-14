package com.example.jetcomposewithcleanarchitecture.presentation.bike_network_detail.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkDetailEntity

@Composable
fun BikeNetworkDetailComposable(modifier: Modifier, bikeNetworkDetail: BikeNetworkDetailEntity?) {
    Text(text = bikeNetworkDetail?.name ?: "Name not found.", modifier = modifier)
}