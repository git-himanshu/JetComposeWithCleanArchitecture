package com.example.bikeNetwork.data.datasource


import com.example.common.model.Result
import com.example.core.network.dto.BikeNetworkDetailResponseDto
import com.example.core.network.dto.BikeNetworkListResponseDto

interface BikeNetworkDataSource {
    suspend fun getBikeNetworkList(): Result<BikeNetworkListResponseDto>
    suspend fun getBikeNetworkDetail(networkId: String): Result<BikeNetworkDetailResponseDto>
}
