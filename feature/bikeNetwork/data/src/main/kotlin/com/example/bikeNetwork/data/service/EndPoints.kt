package com.example.bikeNetwork.data.service

const val BASE_URL = "https://api.citybik.es"

class EndPoints {
    companion object {
        const val BIKE_NETWORK_LIST_ENDPOINT = "v2/networks"
        const val BIKE_NETWORK_DETAIL_ENDPOINT = "v2/networks/{networkId}"
    }
}