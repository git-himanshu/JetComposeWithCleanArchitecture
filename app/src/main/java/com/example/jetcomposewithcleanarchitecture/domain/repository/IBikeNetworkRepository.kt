package com.example.jetcomposewithcleanarchitecture.domain.repository

import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkDetailEntity
import com.example.jetcomposewithcleanarchitecture.model.Result
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworksEntity
import kotlinx.coroutines.flow.Flow

interface IBikeNetworkRepository {
    suspend fun getBikeNetworkList(): Flow<Result<BikeNetworksEntity>?>
    suspend fun getBikeNetworkDetail(networkId:String): Flow<Result<BikeNetworkDetailEntity>?>
}