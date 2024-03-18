package com.example.domain.usecase

import com.example.common.model.Result
import com.example.domain.entity.BikeNetworkDetailEntity
import com.example.domain.repository.BikeNetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BikeNetworkDetailUseCaseImpl @Inject constructor(private val repository: BikeNetworkRepository) :
    BikeNetworkDetailUseCase {
    override suspend fun getDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>> =
        repository.getBikeNetworkDetail(networkId)
}