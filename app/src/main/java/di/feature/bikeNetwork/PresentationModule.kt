package di.feature.bikeNetwork

import com.example.bikeNetwork.domain.usecase.BikeNetworkUseCase
import com.example.bikeNetwork.domain.usecase.IBikeNetworkUseCase
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