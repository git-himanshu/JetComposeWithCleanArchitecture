package com.example.feature.bikeNetwork.presentation.fake

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.bikeNetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBikeNetworkUseCase : IBikeNetworkUseCase {
    override suspend fun getList(): Flow<Result<BikeNetworksEntity>> = flow {

    }

    override suspend fun getDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>> =
        flow {

        }

}