package di.feature.bikeNetwork

import com.example.bikeNetwork.data.datasource.BikeNetworkDataSource
import com.example.bikeNetwork.data.dto.BikeNetworkDetailResponseDto
import com.example.bikeNetwork.data.dto.BikeNetworkDto
import com.example.bikeNetwork.data.remotedatasource.BikeNetworkRemoteDataSource
import com.example.bikeNetwork.data.remotedatasource.modelMapper.BikeNetworkDetailRemoteDataMapper
import com.example.bikeNetwork.data.remotedatasource.modelMapper.BikeNetworkRemoteListDataMapper
import com.example.bikeNetwork.data.remotedatasource.modelMapper.ModelMapper
import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworkEntity
import com.example.client.NetworkClient
import com.example.core.network.NetworkClientImpl
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
