package di.feature.bikeNetwork

import com.example.bikeNetwork.data.datasource.IBikeNetworkDataSource
import com.example.bikeNetwork.data.remotedatasource.BikeNetworkRemoteDataSource
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
