package com.example.jetcomposewithcleanarchitecture.domain.usecase

import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkDetailEntity
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworksEntity
import com.example.jetcomposewithcleanarchitecture.domain.repository.IBikeNetworkRepository
import com.example.jetcomposewithcleanarchitecture.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BikeNetworkUseCase @Inject constructor(private val repository: IBikeNetworkRepository):IBikeNetworkUseCase {
    override suspend fun getList(): Flow<Result<BikeNetworksEntity>?> = repository.getBikeNetworkList()
    override suspend fun getDetail(networkId:String): Flow<Result<BikeNetworkDetailEntity>?> = repository.getBikeNetworkDetail(networkId)
}