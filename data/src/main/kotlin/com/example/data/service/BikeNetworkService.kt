package com.example.data.service

import com.example.data.dto.BikeNetworkDetailResponseDto
import com.example.data.dto.BikeNetworkListResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

object Constants {
    const val NETWORK_ID = "networkId"
}

interface BikeNetworkService {
    @GET(EndPoints.BIKE_NETWORK_LIST_ENDPOINT)
    suspend fun getBikeNetworkList(): Response<BikeNetworkListResponseDto>

    @GET(EndPoints.BIKE_NETWORK_DETAIL_ENDPOINT)
    suspend fun getBikeNetworkDetail(@Path(Constants.NETWORK_ID) networkId: String): Response<BikeNetworkDetailResponseDto>
}