package com.example.testing.testData

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

const val NETWORK_ID = "network_id"
const val NETWORK_ID_VALUE = "1"
const val SERVER_ERROR = "Server not reachable"
const val GENERIC_ERROR = "Some thing went wrong!"
const val FIRST_NAME = "New Delhi Bikers club"
const val FIRST_CITY = "New Delhi"
const val FIRST_COUNTRY = "India"
const val ERROR_TEST = "Error"

const val SECOND_NAME = "London Bikers club"
const val SECOND_CITY = "London"
const val SECOND_COUNTRY = "UK"

val bikeNetwork1 = BikeNetworkEntity(
    id = "1",
    name = FIRST_NAME,
    city = FIRST_CITY,
    country = FIRST_COUNTRY
)
val bikeNetwork2 = BikeNetworkEntity(
    id = "2",
    name = SECOND_NAME,
    city = SECOND_CITY,
    country = SECOND_COUNTRY
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
    name = FIRST_NAME,
    city = FIRST_CITY,
    country = FIRST_COUNTRY,
    stations = stations
)

val networkDto1 = BikeNetworkDto(
    id = "1",
    name = FIRST_NAME,
    location = Location(
        city = FIRST_CITY,
        country = FIRST_COUNTRY,
    )
)

val networkDto2 = BikeNetworkDto(
    id = "2",
    name = SECOND_NAME,
    location = Location(
        city = SECOND_CITY,
        country = SECOND_COUNTRY
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
        id = NETWORK_ID,
        name = FIRST_NAME,
        location = Location(
            city = SECOND_CITY,
            country = SECOND_COUNTRY
        ),
        stations = stationsDto,
    )
)