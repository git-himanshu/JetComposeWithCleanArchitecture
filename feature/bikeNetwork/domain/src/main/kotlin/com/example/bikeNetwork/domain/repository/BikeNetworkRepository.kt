package com.example.bikeNetwork.domain.repository

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.common.model.Result
import kotlinx.coroutines.flow.Flow

interface BikeNetworkRepository {
    suspend fun getBikeNetworkList(): Flow<Result<BikeNetworksEntity>>
    suspend fun getBikeNetworkDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>>
}