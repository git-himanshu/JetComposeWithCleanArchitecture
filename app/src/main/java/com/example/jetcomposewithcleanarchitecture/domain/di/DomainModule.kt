package com.example.jetcomposewithcleanarchitecture.domain.di

import com.example.jetcomposewithcleanarchitecture.data.repository.BikeNetworkRepository
import com.example.jetcomposewithcleanarchitecture.domain.repository.IBikeNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindIBikeNetworkRepository(bikeNetworkRepository: BikeNetworkRepository): IBikeNetworkRepository

}