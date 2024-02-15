package com.example.jetcomposewithcleanarchitecture.data.repository

import com.example.jetcomposewithcleanarchitecture.data.datasource.IBikeNetworkDataSource
import com.example.jetcomposewithcleanarchitecture.data.model_mapper.BikeNetworkDetailRemoteDataMapper
import com.example.jetcomposewithcleanarchitecture.data.model_mapper.BikeNetworkRemoteListDataMapper
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkDetailEntity
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworksEntity
import com.example.jetcomposewithcleanarchitecture.domain.repository.IBikeNetworkRepository
import com.example.jetcomposewithcleanarchitecture.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BikeNetworkRepository @Inject constructor(
    private val dataSource: IBikeNetworkDataSource,
    private val networkListModelMapper: BikeNetworkRemoteListDataMapper,
    private val networkDetailModelMapper: BikeNetworkDetailRemoteDataMapper
) : IBikeNetworkRepository {
    override suspend fun getBikeNetworkList(): Flow<Result<BikeNetworksEntity>?> {
        return flow<Result<BikeNetworksEntity>?> {
            emit(Result.loading())
            val resultDto = dataSource.getBikeNetworkList()
            when (resultDto.status) {
                Result.Status.ERROR -> {
                    emit(Result.error(error = resultDto.error, message = resultDto.error?.status_message ?: ""))
                }

                Result.Status.SUCCESS -> {
                    val resultEntity = resultDto.data?.bikeNetworks?.map {
                        networkListModelMapper.fromDtoToEntity(it)
                    }
                    emit(Result.success(BikeNetworksEntity(resultEntity)))
                }

                Result.Status.LOADING -> {
                    emit(Result.loading())
                }
            }

        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getBikeNetworkDetail(networkId:String): Flow<Result<BikeNetworkDetailEntity>?> {
        return flow<Result<BikeNetworkDetailEntity>?> {
            emit(Result.loading())
            val resultDto = dataSource.getBikeNetworkDetail(networkId)
            when (resultDto.status) {
                Result.Status.ERROR -> {
                    emit(Result.error(error = resultDto.error, message = resultDto.error?.status_message ?: ""))
                }

                Result.Status.SUCCESS -> {
                    val resultEntity = resultDto.data?.let {
                        networkDetailModelMapper.fromDtoToEntity(
                            it
                        )
                    }
                    emit(Result.success(resultEntity))
                }

                Result.Status.LOADING -> {
                    emit(Result.loading())
                }
            }

        }.flowOn(Dispatchers.IO)
    }
}