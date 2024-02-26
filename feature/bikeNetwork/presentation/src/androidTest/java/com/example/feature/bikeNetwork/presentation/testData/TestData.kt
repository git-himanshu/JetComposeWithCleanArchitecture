package com.example.feature.bikeNetwork.presentation.testData

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworkEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.bikeNetwork.domain.entity.Station

const val networkId = "network_id"
const val networkIdValue = "1"
const val serverError = "Server not reachable"
const val genericError = "Some thing went wrong!"
const val errorText = "Error"
const val netWorkId = "1"

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

val station1 = Station(
    id = "1",
    freeBikes = 10,
    name = "CP",
    address = "Inner Circle",
    emptySlot = 5,
    slots = 15
)

val station2 = Station(
    id = "2",
    freeBikes = 8,
    name = "South Ex",
    address = "South extension",
    emptySlot = 12,
    slots = 20
)

val stations = listOf(
    station1,
    station2
)

const val firstName = "New Delhi Bikers club"
const val firstCity = "New Delhi"
const val firstCountry = "India"

const val secondName = "London Bikers club"
const val secondCity = "London"
const val secondCountry = "UK"

val networkEntity = BikeNetworkDetailEntity(
    id = "1",
    name = firstName,
    href = "",
    city = firstCity,
    country = firstCountry,
    stations = stations
)

val networks = listOf(
    BikeNetworkEntity(
        id = "1",
        href = "",
        name = firstName,
        city = firstCity,
        country = firstCountry
    ),
    BikeNetworkEntity(
        id = "2",
        href = "",
        name = secondName,
        city = secondCity,
        country = secondCountry
    )
)