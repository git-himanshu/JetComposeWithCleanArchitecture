package com.example.domain.repository

import com.example.common.model.Result
import com.example.domain.entity.BikeNetworkDetailEntity
import com.example.domain.entity.BikeNetworksEntity
import kotlinx.coroutines.flow.Flow

interface BikeNetworkRepository {
    suspend fun getBikeNetworkList(): Flow<Result<BikeNetworksEntity>>
    suspend fun getBikeNetworkDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>>
}