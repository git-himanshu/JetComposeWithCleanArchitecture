package com.example.domain.usecase

import com.example.common.model.Result
import com.example.domain.entity.BikeNetworkDetailEntity
import kotlinx.coroutines.flow.Flow

interface BikeNetworkDetailUseCase {
    suspend fun getDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>>
}