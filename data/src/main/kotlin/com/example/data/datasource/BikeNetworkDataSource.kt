package com.example.data.datasource


import com.example.common.model.Result
import com.example.data.dto.BikeNetworkDetailResponseDto
import com.example.data.dto.BikeNetworkListResponseDto

interface BikeNetworkDataSource {
    suspend fun getBikeNetworkList(): Result<BikeNetworkListResponseDto>
    suspend fun getBikeNetworkDetail(networkId: String): Result<BikeNetworkDetailResponseDto>
}
