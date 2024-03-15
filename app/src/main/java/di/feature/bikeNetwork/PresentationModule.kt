package di.feature.bikeNetwork

import com.example.bikeNetwork.domain.usecase.BikeNetworkDetailUseCase
import com.example.bikeNetwork.domain.usecase.BikeNetworkListUseCase
import com.example.bikeNetwork.domain.usecase.BikeNetworkUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PresentationModule {
    @Binds
    abstract fun bindBikeNetworkListUseCase(bikeNetworkListUseCase: BikeNetworkUseCaseImpl): BikeNetworkListUseCase

    @Binds
    abstract fun bindBikeNetworkDetailUseCase(bikeNetworkDetailUseCase: BikeNetworkUseCaseImpl): BikeNetworkDetailUseCase

}