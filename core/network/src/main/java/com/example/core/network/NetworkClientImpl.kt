package com.example.core.network

import com.example.client.NetworkClient
import com.example.common.model.Error
import com.example.common.model.Result
import com.example.core.network.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkClientImpl @Inject constructor(private val retrofit: Retrofit) : NetworkClient {
    override suspend fun <T> executeRequest(
        request: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val response = request.invoke()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                val errorResponse = ErrorUtils.parseError(response, retrofit)
                Result.error(
                    errorResponse?.statusMessage ?: Constants.DEFAULT_ERROR_MESSAGE,
                    errorResponse
                )
            }
        } catch (e: Throwable) {
            print(e.printStackTrace())
            Result.error(
                e.message ?: Constants.UNKNOWN_ERROR,
                Error(statusMessage = e.message ?: Constants.UNKNOWN_ERROR)
            )
        }
    }
}