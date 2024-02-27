package com.example.feature.bikeNetwork.presentation.previewData

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.Station

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
const val networkId = "1"

val networkEntity = BikeNetworkDetailEntity(
    id = "1",
    name = firstName,
    href = "",
    city = firstCity,
    country = firstCountry,
    stations = stations
)