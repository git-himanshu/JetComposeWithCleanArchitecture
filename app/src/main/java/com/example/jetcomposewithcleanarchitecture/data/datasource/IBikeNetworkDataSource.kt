package com.example.jetcomposewithcleanarchitecture.data.datasource
import com.example.jetcomposewithcleanarchitecture.model.Result
import com.example.jetcomposewithcleanarchitecture.network.dto.BikeNetworkDetailResponseDto
import com.example.jetcomposewithcleanarchitecture.network.dto.BikeNetworkListResponseDto

interface IBikeNetworkDataSource {
    suspend fun getBikeNetworkList(): Result<BikeNetworkListResponseDto>
    suspend fun getBikeNetworkDetail(networkId:String): Result<BikeNetworkDetailResponseDto>
}