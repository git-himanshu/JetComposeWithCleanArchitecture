package com.example.bikenetwork.domain.entity

public data class BikeNetworkEntity(
    val id: String,
    val name: String,
    val href: String,
    val city: String,
    val country: String,
)

public data class BikeNetworksEntity(val networks:List<BikeNetworkEntity>)