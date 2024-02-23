package com.example.bikenetwork.domain.usecase

import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.entity.BikeNetworksEntity
import com.example.common.model.Result
import kotlinx.coroutines.flow.Flow

interface IBikeNetworkUseCase {
    suspend fun getList(): Flow<Result<BikeNetworksEntity>>
    suspend fun getDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>>
}