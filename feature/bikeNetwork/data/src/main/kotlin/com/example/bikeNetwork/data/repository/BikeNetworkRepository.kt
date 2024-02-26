package com.example.bikeNetwork.data.repository

import com.example.bikeNetwork.data.datasource.IBikeNetworkDataSource
import com.example.bikeNetwork.data.remotedatasource.modelMapper.BikeNetworkDetailRemoteDataMapper
import com.example.bikeNetwork.data.remotedatasource.modelMapper.BikeNetworkRemoteListDataMapper
import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.bikeNetwork.domain.repository.IBikeNetworkRepository
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
    override suspend fun getBikeNetworkList(): Flow<Result<BikeNetworksEntity>> {
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

    override suspend fun getBikeNetworkDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>> {
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
    ): Flow<Result<S>> {
        return flow {
            when (resultDto.status) {
                Result.Status.ERROR -> {
                    emit(
                        Result.error(
                            error = resultDto.error,
                            message = resultDto.error?.statusMessage
                                ?: Constants.defaultErrorMessage
                        )
                    )
                }

                Result.Status.SUCCESS -> {
                    emit(onSuccess())
                }

            }
        }.flowOn(Dispatchers.IO)
    }
}