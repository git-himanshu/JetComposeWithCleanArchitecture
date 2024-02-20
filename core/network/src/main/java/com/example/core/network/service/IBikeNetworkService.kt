package com.example.core.network.service

import com.example.bikenetwork.data.dto.BikeNetworkDetailResponseDto
import com.example.bikenetwork.data.dto.BikeNetworkListResponseDto
import com.example.network.data_source.EndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface IBikeNetworkService {
    @GET(EndPoints.BIKE_NETWORK_LIST_ENDPOINT)
    suspend fun getBikeNetworkList(): Response<BikeNetworkListResponseDto>

    @GET(EndPoints.BIKE_NETWORK_DETAIL_ENDPOINT)
    suspend fun getBikeNetworkDetail(@Path("networkId") networkId: String): Response<BikeNetworkDetailResponseDto>
}