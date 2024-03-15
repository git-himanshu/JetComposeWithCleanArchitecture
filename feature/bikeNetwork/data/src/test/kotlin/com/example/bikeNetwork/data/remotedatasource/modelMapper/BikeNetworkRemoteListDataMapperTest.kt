package com.example.bikeNetwork.data.remotedatasource.modelMapper


import com.example.testing.testData.createBikeNetworkEntity
import com.example.testing.testData.createNetworkDto
import org.junit.Test

class BikeNetworkRemoteListDataMapperTest {

    private val modelMapper = BikeNetworkRemoteListDataMapper()

    @Test
    fun bikeNetworkRemoteListDataMapper_conversion_from_entity_to_dto_is_success() {
        val bikeNetwork = createBikeNetworkEntity()
        val dto = modelMapper.fromEntityToDto(bikeNetwork)
        assert(dto.id == bikeNetwork.id)
        assert(dto.name == bikeNetwork.name)
        assert(dto.location?.city == bikeNetwork.city)
        assert(dto.location?.country == bikeNetwork.country)
    }

    @Test
    fun bikeNetworkRemoteListDataMapper_conversion_from_dto_to_entity_success() {
        val networkDto = createNetworkDto()
        val entity = modelMapper.fromDtoToEntity(networkDto)
        assert(entity.id == networkDto.id)
        assert(entity.name == networkDto.name)
        assert(entity.city == networkDto.location?.city)
        assert(entity.country == networkDto.location?.country)
    }
}