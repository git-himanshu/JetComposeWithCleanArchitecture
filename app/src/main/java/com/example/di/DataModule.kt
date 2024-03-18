package com.example.di

import com.example.client.NetworkClient
import com.example.core.network.NetworkClientImpl
import com.example.data.datasource.BikeNetworkDataSource
import com.example.data.dto.BikeNetworkDetailResponseDto
import com.example.data.dto.BikeNetworkDto
import com.example.data.remotedatasource.BikeNetworkRemoteDataSource
import com.example.data.remotedatasource.modelMapper.BikeNetworkDetailRemoteDataMapper
import com.example.data.remotedatasource.modelMapper.BikeNetworkRemoteListDataMapper
import com.example.data.remotedatasource.modelMapper.ModelMapper
import com.example.domain.entity.BikeNetworkDetailEntity
import com.example.domain.entity.BikeNetworkEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindBikeNetworkDataSource(bikeNetworkDataSource: BikeNetworkRemoteDataSource): BikeNetworkDataSource

    @Binds
    abstract fun bindBikeNetworkRemoteListDataMapper(bikeNetworkDataSource: BikeNetworkRemoteListDataMapper): ModelMapper<BikeNetworkEntity, BikeNetworkDto>

    @Binds
    abstract fun bindBikeNetworkDetailRemoteDataMapper(bikeNetworkDataSource: BikeNetworkDetailRemoteDataMapper): ModelMapper<BikeNetworkDetailEntity, BikeNetworkDetailResponseDto>

    @Binds
    abstract fun bindNetworkClient(networkClient: NetworkClientImpl): NetworkClient
}
