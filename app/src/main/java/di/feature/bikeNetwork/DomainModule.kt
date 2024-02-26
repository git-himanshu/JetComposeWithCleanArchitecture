package di.feature.bikeNetwork

import com.example.bikeNetwork.data.repository.BikeNetworkRepository
import com.example.bikeNetwork.domain.repository.IBikeNetworkRepository
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