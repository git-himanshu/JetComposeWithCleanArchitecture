package com.example.domain.entity

data class Station(
    val id: String,
    val freeBikes: Int,
    val name: String,
    val address: String,
    val emptySlot: Int,
    val slots: Int,
)