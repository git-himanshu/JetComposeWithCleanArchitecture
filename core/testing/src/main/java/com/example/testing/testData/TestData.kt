package com.example.testing.testData

import com.example.data.dto.BikeNetworkDetail
import com.example.data.dto.BikeNetworkDetailResponseDto
import com.example.data.dto.BikeNetworkDto
import com.example.data.dto.BikeNetworkListResponseDto
import com.example.data.dto.Extra
import com.example.data.dto.Location
import com.example.domain.entity.BikeNetworkDetailEntity
import com.example.domain.entity.BikeNetworkEntity
import com.example.domain.entity.BikeNetworksEntity
import com.example.domain.entity.Station
import com.example.data.dto.Station as StationDto

const val NETWORK_ID = "network_id"
const val NETWORK_ID_VALUE = "1"
const val SERVER_ERROR = "Server not reachable"
const val GENERIC_ERROR = "Some thing went wrong!"
const val NETWORK_NAME = "New Delhi Bikers club"
const val NETWORK_CITY = "New Delhi"
const val NETWORK_COUNTRY = "India"
const val ERROR_TEST = "Error"

const val SECOND_NAME = "London Bikers club"
const val SECOND_CITY = "London"
const val SECOND_COUNTRY = "UK"

const val MEDIA_TYPE = "text/json"
const val SERVER_NOT_REACHABLE = "Server not reachable"
const val SERVER_ERROR_CONTENT = "{'statusCode':503,'statusMessage':$SERVER_NOT_REACHABLE}"
const val SERVER_NOT_REACHABLE_ERROR_CODE = 503

fun createBikeNetworkEntity(
    id: String = NETWORK_ID_VALUE,
    name: String = NETWORK_NAME,
    city: String = NETWORK_CITY,
    country: String = NETWORK_COUNTRY
) = BikeNetworkEntity(
    id = id,
    name = name,
    city = city,
    country = country
)

fun createNetworkList(
    list: List<BikeNetworkEntity> = listOf(
        createBikeNetworkEntity(),
        createBikeNetworkEntity(
            id = "2",
            name = SECOND_NAME,
            city = SECOND_CITY,
            country = SECOND_COUNTRY,
        )
    )
) = list

fun createBikeNetworksEntity(networks: List<BikeNetworkEntity> = createNetworkList()) =
    BikeNetworksEntity(networks = networks)

private val station1 = Station(
    id = "1",
    freeBikes = 10,
    name = "CP",
    address = "Inner Circle",
    emptySlot = 5,
    slots = 15
)

private val station2 = Station(
    id = "2",
    freeBikes = 8,
    name = "South Ex",
    address = "South extension",
    emptySlot = 12,
    slots = 20
)

fun createStationList(list: List<Station> = listOf(station1, station2)) = list

fun createNetworkDetailEntity(
    id: String = NETWORK_ID_VALUE,
    name: String = NETWORK_NAME,
    city: String = NETWORK_CITY,
    country: String = NETWORK_COUNTRY,
    stations: List<Station> = createStationList()
) = BikeNetworkDetailEntity(
    id = id,
    name = name,
    city = city,
    country = country,
    stations = stations
)

fun createNetworkDto(
    id: String = NETWORK_ID_VALUE,
    name: String = NETWORK_NAME,
    location: Location = Location(
        city = NETWORK_CITY,
        country = NETWORK_COUNTRY,
    )
) = BikeNetworkDto(
    id = id,
    name = name,
    location = location
)

private val networksDto = listOf(
    createNetworkDto(),
    createNetworkDto(
        id = "2",
        name = "London Bikers club",
        location = Location(
            city = "London",
            country = "UK",
        )
    )
)

fun createNetworkListDto(networkDtoList: List<BikeNetworkDto> = networksDto) =
    BikeNetworkListResponseDto(networkDtoList)

private val stationsDto = listOf(
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

fun createBikeNetworkDetailResponseDto(
    id: String = NETWORK_ID,
    name: String = NETWORK_NAME,
    location: Location = Location(
        city = SECOND_CITY,
        country = SECOND_COUNTRY
    ),
    stations: List<StationDto> = stationsDto,
) = BikeNetworkDetailResponseDto(
    network = BikeNetworkDetail(
        id = id,
        name = name,
        location = location,
        stations = stations,
    )
)