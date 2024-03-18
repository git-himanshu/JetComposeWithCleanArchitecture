package com.example.domain.usecase

import com.example.common.model.Result
import com.example.domain.entity.BikeNetworksEntity
import com.example.domain.repository.BikeNetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BikeNetworkListUseCaseImpl @Inject constructor(private val repository: BikeNetworkRepository) :
    BikeNetworkListUseCase {
    override suspend fun getList(): Flow<Result<BikeNetworksEntity>> =
        repository.getBikeNetworkList()
}