package com.example.bikenetwork.data.remotedatasource
import com.example.bikenetwork.data.datasource.IBikeNetworkDataSource
import com.example.bikenetwork.data.dto.BikeNetworkDetailResponseDto
import com.example.bikenetwork.data.dto.BikeNetworkListResponseDto
import com.example.common.model.Result
import com.example.core.network.RemoteDataSource
import com.example.core.network.service.IBikeNetworkService
import retrofit2.Retrofit
import javax.inject.Inject

class BikeNetworkRemoteDataSource @Inject constructor(
    private val service: IBikeNetworkService,
    private val retrofit: Retrofit
) : IBikeNetworkDataSource, RemoteDataSource(retrofit=retrofit) {
    override suspend fun getBikeNetworkList(): Result<BikeNetworkListResponseDto> =
        getResponse(
            request = { service.getBikeNetworkList() }
        )

    override suspend fun getBikeNetworkDetail(networkId: String): Result<BikeNetworkDetailResponseDto> =
        getResponse(
            request = { service.getBikeNetworkDetail(networkId) }
        )
}
