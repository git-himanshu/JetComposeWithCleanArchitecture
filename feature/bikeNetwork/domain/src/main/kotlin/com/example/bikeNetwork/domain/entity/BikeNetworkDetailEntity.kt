package com.example.bikeNetwork.domain.entity

data class BikeNetworkDetailEntity(
    val id: String,
    val name: String,
    val city: String,
    val country: String,
    val stations: List<Station>
)