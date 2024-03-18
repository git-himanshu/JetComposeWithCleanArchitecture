package com.example.presentation.list.state

import com.example.domain.entity.BikeNetworkEntity

sealed class ListState {
    data object Idle : ListState()
    data object Loading : ListState()
    data object DataNotFound : ListState()
    data class BikeNetworks(val networks: List<BikeNetworkEntity>) : ListState()
    data class Error(val error: String) : ListState()
}