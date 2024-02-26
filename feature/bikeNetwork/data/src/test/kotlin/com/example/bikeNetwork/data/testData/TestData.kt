package com.example.feature.bikeNetwork.presentation.testData

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworkEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.bikeNetwork.domain.entity.Station
import com.example.core.network.dto.BikeNetworkDetail
import com.example.core.network.dto.BikeNetworkDetailResponseDto
import com.example.core.network.dto.BikeNetworkDto
import com.example.core.network.dto.BikeNetworkListResponseDto
import com.example.core.network.dto.Extra
import com.example.core.network.dto.Location
import com.example.core.network.dto.Station as StationDto

const val networkId = "network_id"
const val networkIdValue = "1"
const val serverError = "Server not reachable"
const val genericError = "Some thing went wrong!"
const val firstName = "New Delhi Bikers club"
const val firstCity = "New Delhi"
const val firstCountry = "India"

const val secondName = "London Bikers club"
const val secondCity = "London"
const val secondCountry = "UK"

val bikeNetwork1 = BikeNetworkEntity(
    id = "1",
    href = "href",
    name = firstName,
    city = firstCity,
    country = firstCountry
)
val bikeNetwork2 = BikeNetworkEntity(
    id = "2",
    href = "href",
    name = secondName,
    city = secondCity,
    country = secondCountry
)

val networkList = listOf(
    bikeNetwork1,
    bikeNetwork2
)

val bikeNetworkEntity = BikeNetworksEntity(networks = networkList)

val bikeNetworkEntityWithEmptyList = BikeNetworksEntity(networks = emptyList())


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

val networkDetailEntity = BikeNetworkDetailEntity(
    id = "1",
    name = firstName,
    href = "href",
    city = firstCity,
    country = firstCountry,
    stations = stations
)

val networkDto1 = BikeNetworkDto(
    id = "1",
    name = firstName,
    href = "href",
    location = Location(
        city = firstCity,
        country = firstCountry,
    )
)

val networkDto2 = BikeNetworkDto(
    id = "2",
    name = secondName,
    href = "href",
    location = Location(
        city = secondCity,
        country = secondCountry
    )
)
val networksDto = listOf(
    networkDto1,
    networkDto2
)

val networkListDto = BikeNetworkListResponseDto(networksDto)

val stationsDto = listOf(
    StationDto(
        id = "1",
        freeBikes = 10,
        name = "South Ex",
        emptySlot = 8,
        extra = Extra(
            address = "South Ex, New Delhi",
            slots = 18
        )
    ),
    StationDto(
        id = "2",
        freeBikes = 12,
        name = "South Ex",
        emptySlot = 8,
        extra = Extra(
            address = "CP, New Delhi",
            slots = 20
        )
    )
)
val networkDetailDto = BikeNetworkDetailResponseDto(
    network = BikeNetworkDetail(
        id = networkId,
        name = firstName,
        href = "href",
        location = Location(
            city = secondCity,
            country = secondCountry
        ),
        stations = stationsDto,
    )
)