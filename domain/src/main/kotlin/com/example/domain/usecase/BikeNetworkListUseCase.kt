package com.example.domain.usecase

import com.example.common.model.Result
import com.example.domain.entity.BikeNetworksEntity
import kotlinx.coroutines.flow.Flow

interface BikeNetworkListUseCase {
    suspend fun getList(): Flow<Result<BikeNetworksEntity>>
}