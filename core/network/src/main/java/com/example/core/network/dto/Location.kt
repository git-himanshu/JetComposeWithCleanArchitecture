package com.example.core.network.dto

import com.google.gson.annotations.SerializedName

data class Location(
        @SerializedName("city")
        val city: String?,
        @SerializedName("country")
        val country: String?
)