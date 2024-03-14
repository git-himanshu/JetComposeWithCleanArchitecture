package com.example.core.network.dto

import com.google.gson.annotations.SerializedName

data class BikeNetworkDto(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("location")
        val location: Location?,
)