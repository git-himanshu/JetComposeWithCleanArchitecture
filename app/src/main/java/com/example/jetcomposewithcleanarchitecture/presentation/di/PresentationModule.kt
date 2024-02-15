package com.example.jetcomposewithcleanarchitecture.presentation.di

import com.example.jetcomposewithcleanarchitecture.domain.usecase.BikeNetworkUseCase
import com.example.jetcomposewithcleanarchitecture.domain.usecase.IBikeNetworkUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PresentationModule {
    @Binds
    abstract fun bindIBikeNetworkUseCase(bikeNetworkUseCase: BikeNetworkUseCase): IBikeNetworkUseCase

}