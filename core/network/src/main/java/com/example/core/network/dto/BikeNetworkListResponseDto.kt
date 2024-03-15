package com.example.core.network.dto

import com.google.gson.annotations.SerializedName

data class BikeNetworkListResponseDto(
    @SerializedName("networks")
    val bikeNetworks: List<BikeNetworkDto>?
)