package com.example.bikenetwork.domain.entity

public data class BikeNetworkDetailEntity(
    val id: String = "",
    val name: String= "",
    val href: String= "",
    val city: String= "",
    val country: String= "",
    val stations: List<Station> = emptyList()
)

public data class Station(
    val id: String,
    val freeBikes: Int,
    val name: String,
    val address: String,
    val emptySlot: Int,
    val slots: Int,
)