package com.example.bikeNetwork.domain.usecase

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.bikeNetwork.domain.repository.BikeNetworkRepository
import com.example.common.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BikeNetworkUseCaseImpl @Inject constructor(private val repository: BikeNetworkRepository) :
        BikeNetworkUseCase {
    override suspend fun getList(): Flow<Result<BikeNetworksEntity>> =
            repository.getBikeNetworkList()

    override suspend fun getDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>> =
            repository.getBikeNetworkDetail(networkId)
}