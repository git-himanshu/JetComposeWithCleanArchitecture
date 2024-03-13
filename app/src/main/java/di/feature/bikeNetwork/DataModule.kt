package di.feature.bikeNetwork

import com.example.bikeNetwork.data.datasource.IBikeNetworkDataSource
import com.example.bikeNetwork.data.remotedatasource.BikeNetworkRemoteDataSource
import com.example.bikeNetwork.data.remotedatasource.modelMapper.BikeNetworkDetailRemoteDataMapper
import com.example.bikeNetwork.data.remotedatasource.modelMapper.BikeNetworkRemoteListDataMapper
import com.example.bikeNetwork.data.remotedatasource.modelMapper.IModelMapper
import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikeNetwork.domain.entity.BikeNetworkEntity
import com.example.core.network.dto.BikeNetworkDetailResponseDto
import com.example.core.network.dto.BikeNetworkDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindIBikeNetworkDataSource(bikeNetworkDataSource: BikeNetworkRemoteDataSource): IBikeNetworkDataSource

    @Binds
    abstract fun bindBikeNetworkRemoteListDataMapper(bikeNetworkDataSource: BikeNetworkRemoteListDataMapper): IModelMapper<BikeNetworkEntity, BikeNetworkDto>

    @Binds
    abstract fun bindBikeNetworkDetailRemoteDataMapper(bikeNetworkDataSource: BikeNetworkDetailRemoteDataMapper): IModelMapper<BikeNetworkDetailEntity, BikeNetworkDetailResponseDto>
}
