package com.example.bikenetwork.domain.repository

import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.entity.BikeNetworksEntity
import com.example.common.model.Result
import kotlinx.coroutines.flow.Flow

interface IBikeNetworkRepository {
    suspend fun getBikeNetworkList(): Flow<Result<BikeNetworksEntity>?>
    suspend fun getBikeNetworkDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>?>
}