package com.example.bikeNetwork.data.remotedatasource.modelMapper

import com.example.testing.testData.bikeNetwork1
import com.example.testing.testData.networkDto1
import org.junit.Test

class BikeNetworkRemoteListDataMapperTest {

    private val modelMapper = BikeNetworkRemoteListDataMapper()

    @Test
    fun bikeNetworkRemoteListDataMapper_conversion_from_entity_to_dto_is_success() {
        val dto = modelMapper.fromEntityToDto(bikeNetwork1)
        assert(dto.id == bikeNetwork1.id)
        assert(dto.name == bikeNetwork1.name)
        assert(dto.href == bikeNetwork1.href)
        assert(dto.location?.city == bikeNetwork1.city)
        assert(dto.location?.country == bikeNetwork1.country)
    }

    @Test
    fun bikeNetworkRemoteListDataMapper_conversion_from_dto_to_entity_success() {
        val entity = modelMapper.fromDtoToEntity(networkDto1)
        assert(entity.id == networkDto1.id)
        assert(entity.name == networkDto1.name)
        assert(entity.href == networkDto1.href)
        assert(entity.city == networkDto1.location?.city)
        assert(entity.country == networkDto1.location?.country)
    }
}