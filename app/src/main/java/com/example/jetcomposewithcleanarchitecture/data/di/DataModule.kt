package com.example.jetcomposewithcleanarchitecture.data.di

import com.example.jetcomposewithcleanarchitecture.data.datasource.IBikeNetworkDataSource
import com.example.jetcomposewithcleanarchitecture.network.data_source.BikeNetworkRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindIBikeNetworkDataSource(bikeNetworkDataSource: BikeNetworkRemoteDataSource): IBikeNetworkDataSource

}