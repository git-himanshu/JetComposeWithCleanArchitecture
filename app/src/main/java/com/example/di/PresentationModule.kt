@file:Suppress("unused", "unused", "unused")

package com.example.di

import com.example.domain.usecase.BikeNetworkDetailUseCase
import com.example.domain.usecase.BikeNetworkDetailUseCaseImpl
import com.example.domain.usecase.BikeNetworkListUseCase
import com.example.domain.usecase.BikeNetworkListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PresentationModule {
    @Binds
    abstract fun bindBikeNetworkListUseCase(bikeNetworkListUseCase: BikeNetworkListUseCaseImpl): BikeNetworkListUseCase

    @Binds
    abstract fun bindBikeNetworkDetailUseCase(bikeNetworkDetailUseCase: BikeNetworkDetailUseCaseImpl): BikeNetworkDetailUseCase

}