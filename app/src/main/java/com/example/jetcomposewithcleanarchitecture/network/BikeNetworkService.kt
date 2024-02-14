package com.example.jetcomposewithcleanarchitecture.network

import com.example.jetcomposewithcleanarchitecture.network.dto.BikeNetworkDetailResponseDto
import com.example.jetcomposewithcleanarchitecture.network.dto.BikeNetworkListResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface BikeNetworkService {
    @GET(EndPoints.BIKE_NETWORK_LIST_ENDPOINT)
    suspend fun getBikeNetworkList(): Response<BikeNetworkListResponseDto>

    @GET(EndPoints.BIKE_NETWORK_DETAIL_ENDPOINT)
    suspend fun getBikeNetworkDetail(@Path("networkId") networkId: String): Response<BikeNetworkDetailResponseDto>
}