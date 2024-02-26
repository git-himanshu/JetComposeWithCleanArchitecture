package com.example.feature.bikeNetwork.presentation.detail.state

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity

sealed class DetailState {
    data object Idle : DetailState()
    data object Loading : DetailState()
    data object DataNotFound : DetailState()
    data class NetworkDetail(val networks: BikeNetworkDetailEntity) : DetailState()
    data class Error(val error: String) : DetailState()
}