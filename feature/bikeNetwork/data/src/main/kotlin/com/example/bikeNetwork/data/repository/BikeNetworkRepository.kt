package com.example.bikeNetwork.data.repository

import com.example.bikeNetwork.data.datasource.IBikeNetworkDataSource
import com.example.bikeNetwork.data.remotedatasource.modelMapper.IModelMapper
import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworkEntity
import com.example.bikeNetwork.domain.entity.BikeNetworksEntity
import com.example.bikeNetwork.domain.repository.IBikeNetworkRepository
import com.example.common.model.Result
import com.example.core.network.Constants
import com.example.core.network.dto.BikeNetworkDetailResponseDto
import com.example.core.network.dto.BikeNetworkDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BikeNetworkRepository @Inject constructor(
    private val dataSource: IBikeNetworkDataSource,
    private val listModelMapper: IModelMapper<BikeNetworkEntity, BikeNetworkDto>,
    private val detailModelMapper: IModelMapper<BikeNetworkDetailEntity, BikeNetworkDetailResponseDto>
) : IBikeNetworkRepository {
    override suspend fun getBikeNetworkList(): Flow<Result<BikeNetworksEntity>> {
        val listDto = dataSource.getBikeNetworkList()
        return {
            val resultEntity = listDto.data?.bikeNetworks?.map {
                listModelMapper.fromDtoToEntity(it)
            }
            Result.success(resultEntity?.let { BikeNetworksEntity(it) })
        }.processDataSourceResponse(
            resultDto = listDto,
        )
    }

    override suspend fun getBikeNetworkDetail(networkId: String): Flow<Result<BikeNetworkDetailEntity>> {
        val detailDto = dataSource.getBikeNetworkDetail(networkId)
        return {
            val resultEntity = detailDto.data?.let {
                detailModelMapper.fromDtoToEntity(
                    it
                )
            }
            Result.success(resultEntity)
        }.processDataSourceResponse(
            resultDto = detailDto,
        )
    }

    private suspend fun <T, S> (() -> Result<S>).processDataSourceResponse(
        resultDto: Result<T>,
    ): Flow<Result<S>> {
        return flow {
            when (resultDto.status) {
                Result.Status.ERROR -> {
                    this.emit(
                        Result.error(
                            error = resultDto.error,
                            message = resultDto.error?.statusMessage
                                ?: Constants.defaultErrorMessage
                        )
                    )
                }

                Result.Status.SUCCESS -> {
                    this.emit(this@processDataSourceResponse())
                }

            }
        }.flowOn(Dispatchers.IO)
    }
}