package com.example.bikeNetwork.data.datasource


import com.example.bikeNetwork.data.dto.BikeNetworkDetailResponseDto
import com.example.bikeNetwork.data.dto.BikeNetworkListResponseDto
import com.example.common.model.Result

interface BikeNetworkDataSource {
    suspend fun getBikeNetworkList(): Result<BikeNetworkListResponseDto>
    suspend fun getBikeNetworkDetail(networkId: String): Result<BikeNetworkDetailResponseDto>
}
