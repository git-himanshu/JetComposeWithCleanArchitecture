package com.example.feature.bikeNetwork.presentation.fake

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.bikeNetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Error
import com.example.common.model.Result
import com.example.feature.bikeNetwork.presentation.detail.ui.testData.networkEntity
import com.example.feature.bikeNetwork.presentation.testData.bikeNetworkEntity
import com.example.feature.bikeNetwork.presentation.testData.bikeNetworkEntityWithEmptyList
import com.example.feature.bikeNetwork.presentation.testData.genericError
import com.example.feature.bikeNetwork.presentation.testData.serverError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBikeNetworkUseCase(private val responseType: ResponseType) : IBikeNetworkUseCase {
    enum class ResponseType {
        SUCCESS_WITH_DATA,
        SUCCESS_WITHOUT_DATA,
        ERROR,
    }

    override suspend fun getList(): Flow<Result<BikeNetworksEntity>> = flow {
        when (responseType) {
            ResponseType.SUCCESS_WITH_DATA -> emit(
                Result(
                    status = Result.Status.SUCCESS,
                    data = bikeNetworkEntity,
                    error = null,
                    message = null
                )
            )

            ResponseType.SUCCESS_WITHOUT_DATA -> emit(
                Result(
                    status = Result.Status.SUCCESS,
                    data = bikeNetworkEntityWithEmptyList,
                    error = null,
                    message = null
                )
            )

            ResponseType.ERROR -> emit(
                Result(
                    status = Result.Status.ERROR,
                    data = null,
                    error = Error(statusCode = 500, statusMessage = "Server not reachable"),
                    message = "Some thing went wrong!"
                )
            )
        }
    }

    override suspend fun getDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>> =
        flow {
            when (responseType) {
                ResponseType.SUCCESS_WITH_DATA -> emit(
                    Result(
                        status = Result.Status.SUCCESS,
                        data = networkEntity,
                        error = null,
                        message = null
                    )
                )

                ResponseType.SUCCESS_WITHOUT_DATA -> emit(
                    Result(
                        status = Result.Status.SUCCESS,
                        data = null,
                        error = null,
                        message = null
                    )
                )

                ResponseType.ERROR -> emit(
                    Result(
                        status = Result.Status.ERROR,
                        data = null,
                        error = Error(statusCode = 500, statusMessage = serverError),
                        message = genericError
                    )
                )
            }
        }
}