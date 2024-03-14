package com.example.core.network.dto

import com.google.gson.annotations.SerializedName

data class BikeNetworkListResponseDto(
    @SerializedName("networks")
    val bikeNetworks: List<BikeNetworkDto>?
)

data class BikeNetworkDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("location")
    val location: Location?,
)

data class Location(
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?
)