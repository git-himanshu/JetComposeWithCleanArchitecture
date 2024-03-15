package com.example.bikeNetwork.domain.usecase

import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.common.model.Result
import kotlinx.coroutines.flow.Flow

interface BikeNetworkListUseCase {
    suspend fun getList(): Flow<Result<BikeNetworksEntity>>
}