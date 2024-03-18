package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class BikeNetworkDetailResponseDto(
    @SerializedName("network")
    val network: BikeNetworkDetail?,
)