package com.example.feature.bikenetwork.presentation.testData

import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.entity.BikeNetworkEntity
import com.example.bikenetwork.domain.entity.Station

val stations = listOf(
    Station(
        id = "1",
        freeBikes = 10,
        name = "CP",
        address = "Canaught Palace",
        emptySlot = 5,
        slots = 15
    ),
    Station(
        id = "2",
        freeBikes = 8,
        name = "South Ex",
        address = "South extension",
        emptySlot = 12,
        slots = 20
    ),
)

val errorText = "Something went wrong"
val firstName = "New Delhi Bikers club"
val firstCity = "New Delhi"
val firstCountry = "India"

val secondName = "London Bikers club"
val secondCity = "London"
val secondCountry = "UK"

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