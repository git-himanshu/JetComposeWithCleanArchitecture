package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class BikeNetworkListResponseDto(
    @SerializedName("networks")
    val bikeNetworks: List<BikeNetworkDto>?
)