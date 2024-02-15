package com.example.jetcomposewithcleanarchitecture.network.data_source

import com.example.jetcomposewithcleanarchitecture.data.datasource.IBikeNetworkDataSource
import com.example.jetcomposewithcleanarchitecture.model.Error
import com.example.jetcomposewithcleanarchitecture.model.Result
import com.example.jetcomposewithcleanarchitecture.network.IBikeNetworkService
import com.example.jetcomposewithcleanarchitecture.network.dto.BikeNetworkDetailResponseDto
import com.example.jetcomposewithcleanarchitecture.network.dto.BikeNetworkListResponseDto
import com.example.jetcomposewithcleanarchitecture.network.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class BikeNetworkRemoteDataSource @Inject constructor(
    private val service: IBikeNetworkService,
    private val retrofit: Retrofit
) : IBikeNetworkDataSource {
    override suspend fun getBikeNetworkList(): Result<BikeNetworkListResponseDto> =
        getResponse(
            request = { service.getBikeNetworkList() },
            defaultErrorMessage = "Error fetching Bike network list."
        )

    override suspend fun getBikeNetworkDetail(networkId: String): Result<BikeNetworkDetailResponseDto> =
        getResponse(
            request = { service.getBikeNetworkDetail(networkId) },
            defaultErrorMessage = "Error fetching Bike network detail."
        )


    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Result<T> {
        return try {
            val response = request.invoke()
            if (response.isSuccessful) {
                return Result.success(response.body())
            } else {
                val errorResponse = ErrorUtils.parseError(response, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            print(e.printStackTrace())
            Result.error("Unknown Error", Error(status_message = "Unknown Error"))
        }
    }
}