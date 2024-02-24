package com.example.feature.bikenetwork.presentation.testData

import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.entity.BikeNetworkEntity
import com.example.bikenetwork.domain.entity.BikeNetworksEntity

private val networkList = listOf<BikeNetworkEntity>(
    BikeNetworkEntity(
        id = "1",
        href = "",
        name = "New Delhi Bikers club",
        city = "New Delhi",
        country = ""
    ),
    BikeNetworkEntity(
        id = "2",
        href = "",
        name = "London Bikers club",
        city = "London",
        country = "India"
    )
)

val bikeNetworkEntityWithEmptyList = BikeNetworksEntity(networks = emptyList())
val bikeNetworkEntity = BikeNetworksEntity(networks = networkList)

val bikeNetworkDetailEntity = BikeNetworkDetailEntity()