package com.example.data.remotedatasource.modelMapper

import com.example.data.dto.BikeNetworkDto
import com.example.data.dto.Location
import com.example.domain.entity.BikeNetworkEntity
import javax.inject.Inject


class BikeNetworkRemoteListDataMapper @Inject constructor() :
    ModelMapper<BikeNetworkEntity, BikeNetworkDto> {
    override fun fromEntityToDto(entity: BikeNetworkEntity): BikeNetworkDto {
        return BikeNetworkDto(
            id = entity.id,
            name = entity.name,
            Location(
                city = entity.city,
                country = entity.country
            )
        )
    }

    override fun fromDtoToEntity(dto: BikeNetworkDto): BikeNetworkEntity {
        return BikeNetworkEntity(
            id = dto.id ?: "",
            name = dto.name ?: "",
            city = dto.location?.city ?: "",
            country = dto.location?.country ?: ""
        )
    }
}