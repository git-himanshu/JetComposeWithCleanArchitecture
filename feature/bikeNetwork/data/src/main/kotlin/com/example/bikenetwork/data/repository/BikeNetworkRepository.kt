package com.example.bikenetwork.data.repository

import com.example.bikenetwork.data.datasource.IBikeNetworkDataSource
import com.example.bikenetwork.data.remotedatasource.model_mapper.BikeNetworkDetailRemoteDataMapper
import com.example.bikenetwork.data.remotedatasource.model_mapper.BikeNetworkRemoteListDataMapper
import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.entity.BikeNetworksEntity
import com.example.bikenetwork.domain.repository.IBikeNetworkRepository
import com.example.common.model.Result
import com.example.core.network.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BikeNetworkRepository @Inject constructor(
    private val dataSource: IBikeNetworkDataSource,
    private val listModelMapper: BikeNetworkRemoteListDataMapper,
    private val detailModelMapper: BikeNetworkDetailRemoteDataMapper
) : IBikeNetworkRepository {
    override suspend fun getBikeNetworkList(): Flow<Result<BikeNetworksEntity>?> {
        val listDto = dataSource.getBikeNetworkList()
        return processDataSourceResponse(
            resultDto = listDto,
            onSuccess = {
                val resultEntity = listDto.data?.bikeNetworks?.map {
                    listModelMapper.fromDtoToEntity(it)
                }
                Result.success(resultEntity?.let { BikeNetworksEntity(it) })
            },
        )
    }

    override suspend fun getBikeNetworkDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>?> {
        val detailDto = dataSource.getBikeNetworkDetail(networkId)
        return processDataSourceResponse(
            resultDto = detailDto,
            onSuccess = {
                val resultEntity = detailDto.data?.let {
                    detailModelMapper.fromDtoToEntity(
                        it
                    )
                }
                Result.success(resultEntity)
            },
        )
    }

    private suspend fun <T, S> processDataSourceResponse(
        resultDto: Result<T>,
        onSuccess: () -> Result<S>,
    ): Flow<Result<S>?> {
        return flow<Result<S>?> {
            emit(Result.loading())
            when (resultDto.status) {
                Result.Status.ERROR -> {
                    emit(
                        Result.error(
                            error = resultDto.error,
                            message = resultDto.error?.status_message
                                ?: Constants.defaultErrorMessage
                        )
                    )
                }

                Result.Status.SUCCESS -> {
                    emit(onSuccess())
                }

                Result.Status.LOADING -> {
                    emit(Result.loading())
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}