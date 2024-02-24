package com.example.feature.bikenetwork.presentation.fake

import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.entity.BikeNetworksEntity
import com.example.bikenetwork.domain.usecase.IBikeNetworkUseCase
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