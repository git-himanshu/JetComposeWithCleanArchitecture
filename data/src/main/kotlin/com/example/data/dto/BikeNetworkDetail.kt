package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class BikeNetworkDetail(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("stations")
    val stations: List<Station>?,
)