package com.example.feature.bikenetwork.presentation.fake

import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.entity.BikeNetworksEntity
import com.example.bikenetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Error
import com.example.common.model.Result
import com.example.feature.bikenetwork.presentation.testData.bikeNetworkDetailEntity
import com.example.feature.bikenetwork.presentation.testData.bikeNetworkEntity
import com.example.feature.bikenetwork.presentation.testData.bikeNetworkEntityWithEmptyList
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
                    error = Error(status_code = 500, status_message = "Server not reachable"),
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
                        data = bikeNetworkDetailEntity,
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
                        error = Error(status_code = 500, status_message = "Server not reachable"),
                        message = "Some thing went wrong!"
                    )
                )
            }
        }
}