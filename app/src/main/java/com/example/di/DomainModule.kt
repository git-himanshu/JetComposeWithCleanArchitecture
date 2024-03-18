package com.example.di

import com.example.data.repository.BikeNetworkRepositoryImpl
import com.example.domain.repository.BikeNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindIBikeNetworkRepository(bikeNetworkRepository: BikeNetworkRepositoryImpl): BikeNetworkRepository

}