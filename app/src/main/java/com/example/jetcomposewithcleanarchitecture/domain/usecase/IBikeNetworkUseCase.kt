package com.example.jetcomposewithcleanarchitecture.domain.usecase

import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkDetailEntity
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworksEntity
import com.example.jetcomposewithcleanarchitecture.model.Result
import kotlinx.coroutines.flow.Flow

interface IBikeNetworkUseCase {
    suspend fun getList(): Flow<Result<BikeNetworksEntity>?>
    suspend fun getDetail(networkId:String): Flow<Result<BikeNetworkDetailEntity>?>
}