package com.example.feature.bikeNetwork.presentation.testData

import com.example.bikeNetwork.domain.entity.BikeNetworkEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity

const val networkId = "network_id"
const val networkIdValue = "1"
const val serverError = "Server not reachable"
const val genericError = "Some thing went wrong!"

private val networkList = listOf(
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