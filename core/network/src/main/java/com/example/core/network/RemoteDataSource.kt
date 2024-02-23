package com.example.core.network

import com.example.common.model.Error
import com.example.common.model.Result
import com.example.core.network.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit

abstract class RemoteDataSource(private val retrofit: Retrofit) {
    suspend fun <T> getResponse(
        request: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val response = request.invoke()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                val errorResponse = ErrorUtils.parseError(response, retrofit)
                Result.error(
                    errorResponse?.status_message ?: Constants.defaultErrorMessage,
                    errorResponse
                )
            }
        } catch (e: Throwable) {
            print(e.printStackTrace())
            Result.error(
                e.message ?: Constants.unknownError,
                Error(status_message = e.message ?: Constants.unknownError)
            )
        }
    }
}