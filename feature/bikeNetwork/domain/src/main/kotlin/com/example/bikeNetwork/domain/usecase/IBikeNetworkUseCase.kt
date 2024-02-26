package com.example.bikeNetwork.domain.usecase

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.common.model.Result
import kotlinx.coroutines.flow.Flow

interface IBikeNetworkUseCase {
    suspend fun getList(): Flow<Result<BikeNetworksEntity>>
    suspend fun getDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>>
}