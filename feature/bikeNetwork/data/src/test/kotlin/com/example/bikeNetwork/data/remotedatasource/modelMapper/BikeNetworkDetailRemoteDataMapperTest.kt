package com.example.bikeNetwork.data.remotedatasource.modelMapper

import com.example.testing.testData.networkDetailDto
import com.example.testing.testData.networkDetailEntity
import org.junit.Test

class BikeNetworkDetailRemoteDataMapperTest {
    private val modelMapper = BikeNetworkDetailRemoteDataMapper()

    @Test
    fun bikeNetworkRemoteDetailDataMapper_conversion_from_entity_to_dto_is_success() {
        val dto = modelMapper.fromEntityToDto(networkDetailEntity)
        assert(dto.network?.id == networkDetailEntity.id)
        assert(dto.network?.name == networkDetailEntity.name)
        assert(dto.network?.href == networkDetailEntity.href)
        assert(dto.network?.location?.city == networkDetailEntity.city)
        assert(dto.network?.location?.country == networkDetailEntity.country)
        dto.network?.stations?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun bikeNetworkRemoteDetailDataMapper_conversion_from_dto_to_entity_success() {
        val entity = modelMapper.fromDtoToEntity(networkDetailDto)
        assert(entity.id == networkDetailDto.network?.id)
        assert(entity.name == networkDetailDto.network?.name)
        assert(entity.href == networkDetailDto.network?.href)
        assert(entity.city == networkDetailDto.network?.location?.city)
        assert(entity.country == networkDetailDto.network?.location?.country)
        assert(entity.stations.isNotEmpty())
    }
}