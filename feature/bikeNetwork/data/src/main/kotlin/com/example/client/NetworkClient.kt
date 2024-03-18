package com.example.client

import com.example.common.model.Result
import retrofit2.Response

interface NetworkClient {
    suspend fun <T> executeRequest(
        request: suspend () -> Response<T>
    ): Result<T>
}