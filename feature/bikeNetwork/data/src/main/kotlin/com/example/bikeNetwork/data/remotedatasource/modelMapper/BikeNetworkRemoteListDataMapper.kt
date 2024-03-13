package com.example.bikeNetwork.data.remotedatasource.modelMapper

import com.example.bikeNetwork.domain.entity.BikeNetworkEntity
import com.example.core.network.dto.BikeNetworkDto
import com.example.core.network.dto.Location
import javax.inject.Inject


class BikeNetworkRemoteListDataMapper @Inject constructor() :
    IModelMapper<BikeNetworkEntity, BikeNetworkDto> {
    override fun fromEntityToDto(entity: BikeNetworkEntity): BikeNetworkDto {
        return BikeNetworkDto(
            id = entity.id,
            name = entity.name,
            href = entity.href,
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
            href = dto.href ?: "",
            city = dto.location?.city ?: "",
            country = dto.location?.country ?: ""
        )
    }
}