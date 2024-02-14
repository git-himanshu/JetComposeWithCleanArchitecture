package com.example.jetcomposewithcleanarchitecture.network.dto

import com.google.gson.annotations.SerializedName

data class BikeNetworkDetailResponseDto(
    @SerializedName("network")
    val network: BikeNetworkDetail?,
)

data class BikeNetworkDetail(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("stations")
    val stations: List<Station>?,
)
data class Station(
    @SerializedName("id")
    val id: String?,
    @SerializedName("free_bikes")
    val freeBikes: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("empty_slots")
    val emptySlot: Int?,
    @SerializedName("extra")
    val extra: Extra,
)

data class Extra(
    @SerializedName("address")
    val address: String?,
    @SerializedName("slots")
    val slots: Int?,
)
