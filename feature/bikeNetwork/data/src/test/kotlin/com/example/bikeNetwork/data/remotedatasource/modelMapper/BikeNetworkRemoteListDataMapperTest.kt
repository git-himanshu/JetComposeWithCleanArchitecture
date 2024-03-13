package com.example.bikeNetwork.data.remotedatasource.modelMapper

import com.example.testing.testData.bikeNetwork1
import com.example.testing.testData.networkDto1
import org.junit.Test

class BikeNetworkRemoteListDataMapperTest {

    private val modelMapper = BikeNetworkRemoteListDataMapper()

    @Test
    fun bikeNetworkRemoteListDataMapper_conversion_from_entity_to_dto_is_success() {
        val dto = modelMapper.fromEntityToDto(bikeNetwork1)
        dto.id?.let { assert(it.isNotEmpty()) }
        dto.name?.let { assert(it.isNotEmpty()) }
        dto.href?.let { assert(it.isNotEmpty()) }
        assert(dto.location != null)
        dto.location?.city?.let { assert(it.isNotEmpty()) }
        dto.location?.country?.let { assert(it.isNotEmpty()) }
    }

    @Test
    fun bikeNetworkRemoteListDataMapper_conversion_from_dto_to_entity_success() {
        val entity = modelMapper.fromDtoToEntity(networkDto1)
        assert(entity.id.isNotEmpty())
        assert(entity.name.isNotEmpty())
        assert(entity.href.isNotEmpty())
        assert(entity.city.isNotEmpty())
        assert(entity.country.isNotEmpty())
    }
}