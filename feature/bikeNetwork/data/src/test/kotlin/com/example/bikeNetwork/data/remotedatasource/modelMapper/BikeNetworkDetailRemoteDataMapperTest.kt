package com.example.bikeNetwork.data.remotedatasource.modelMapper

import com.example.testing.testData.networkDetailDto
import com.example.testing.testData.networkDetailEntity
import org.junit.Test

class BikeNetworkDetailRemoteDataMapperTest {
    private val modelMapper = BikeNetworkDetailRemoteDataMapper()

    @Test
    fun bikeNetworkRemoteListDataMapper_fromEntityToDto() {
        val dto = modelMapper.fromEntityToDto(networkDetailEntity)
        dto.network?.id?.isNotEmpty()?.let { assert(it) }
        dto.network?.name?.isNotEmpty()?.let { assert(it) }
        dto.network?.href?.isNotEmpty()?.let { assert(it) }
        assert(dto.network?.location != null)
        dto.network?.location?.city?.isNotEmpty()?.let { assert(it) }
        dto.network?.location?.country?.isNotEmpty()?.let { assert(it) }
        dto.network?.stations?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun bikeNetworkRemoteListDataMapper_fromDtoToEntity() {
        val entity = modelMapper.fromDtoToEntity(networkDetailDto)
        assert(entity.id.isNotEmpty())
        assert(entity.name.isNotEmpty())
        assert(entity.href.isNotEmpty())
        assert(entity.city.isNotEmpty())
        assert(entity.country.isNotEmpty())
        assert(entity.stations.isNotEmpty())
    }
}