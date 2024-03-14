package com.example.bikeNetwork.data.remotedatasource

import com.example.bikeNetwork.data.datasource.BikeNetworkDataSource
import com.example.common.model.Result
import com.example.core.network.RemoteDataSource
import com.example.core.network.dto.BikeNetworkDetailResponseDto
import com.example.core.network.dto.BikeNetworkListResponseDto
import com.example.core.network.service.BikeNetworkService
import retrofit2.Retrofit
import javax.inject.Inject

class BikeNetworkRemoteDataSource @Inject constructor(
        private val service: BikeNetworkService,
        retrofit: Retrofit
) : RemoteDataSource(retrofit = retrofit), BikeNetworkDataSource {
    override suspend fun getBikeNetworkList(): Result<BikeNetworkListResponseDto> =
            getResponse(
                    request = { service.getBikeNetworkList() }
            )

    override suspend fun getBikeNetworkDetail(networkId: String): Result<BikeNetworkDetailResponseDto> =
            getResponse(
                    request = { service.getBikeNetworkDetail(networkId) }
            )
}
