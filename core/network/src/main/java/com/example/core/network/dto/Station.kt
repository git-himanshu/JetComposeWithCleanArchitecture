package com.example.core.network.dto

import com.google.gson.annotations.SerializedName

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