package com.example.jetcomposewithcleanarchitecture.domain.entity

data class BikeNetworkEntity(
    val id: String,
    val name: String?,
    val href: String?,
    val city: String?,
    val country: String?,
)

data class BikeNetworksEntity(val networks:List<BikeNetworkEntity>?)