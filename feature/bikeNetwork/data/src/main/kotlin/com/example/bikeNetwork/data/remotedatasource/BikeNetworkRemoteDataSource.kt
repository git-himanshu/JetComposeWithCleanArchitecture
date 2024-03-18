package com.example.bikeNetwork.data.remotedatasource

import com.example.bikeNetwork.data.datasource.BikeNetworkDataSource
import com.example.bikeNetwork.data.dto.BikeNetworkDetailResponseDto
import com.example.bikeNetwork.data.dto.BikeNetworkListResponseDto
import com.example.bikeNetwork.data.service.BikeNetworkService
import com.example.client.NetworkClient
import com.example.common.model.Result
import javax.inject.Inject

class BikeNetworkRemoteDataSource @Inject constructor(
    private val service: BikeNetworkService,
    private val client: NetworkClient
) : BikeNetworkDataSource {
    override suspend fun getBikeNetworkList(): Result<BikeNetworkListResponseDto> =
        client.executeRequest { service.getBikeNetworkList() }

    override suspend fun getBikeNetworkDetail(networkId: String): Result<BikeNetworkDetailResponseDto> =
        client.executeRequest { service.getBikeNetworkDetail(networkId) }
}
