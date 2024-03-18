package com.example.bikeNetwork.data.dto

import com.google.gson.annotations.SerializedName

data class Extra(
    @SerializedName("address")
    val address: String?,
    @SerializedName("slots")
    val slots: Int?,
)