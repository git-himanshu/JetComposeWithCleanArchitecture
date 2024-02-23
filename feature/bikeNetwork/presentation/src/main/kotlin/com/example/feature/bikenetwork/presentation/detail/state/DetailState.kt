package com.example.feature.bikenetwork.presentation.detail.state

import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity

sealed class DetailState {
    data object Idle : DetailState()
    data object Loading : DetailState()
    data object DataNotFound : DetailState()
    data class NetworkDetail(val networks: BikeNetworkDetailEntity) : DetailState()
    data class Error(val error: String) : DetailState()
}