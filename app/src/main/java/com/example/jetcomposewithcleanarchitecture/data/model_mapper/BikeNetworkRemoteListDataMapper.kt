package com.example.jetcomposewithcleanarchitecture.data.model_mapper

import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkDetailEntity
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkEntity
import com.example.jetcomposewithcleanarchitecture.domain.entity.Station
import com.example.jetcomposewithcleanarchitecture.network.dto.BikeNetworkDetail
import com.example.jetcomposewithcleanarchitecture.network.dto.BikeNetworkDetailResponseDto
import com.example.jetcomposewithcleanarchitecture.network.dto.BikeNetworkDto
import com.example.jetcomposewithcleanarchitecture.network.dto.Extra
import com.example.jetcomposewithcleanarchitecture.network.dto.Location
import javax.inject.Inject
import com.example.jetcomposewithcleanarchitecture.network.dto.Station as StationDto


class BikeNetworkRemoteListDataMapper @Inject constructor() :
    IModelMapper<BikeNetworkEntity, BikeNetworkDto> {
    override fun fromEntityToDto(entity: BikeNetworkEntity): BikeNetworkDto {
        return BikeNetworkDto(
            id = entity.id,
            name = entity.name,
            href = entity.href,
            Location(city = entity.city, country = entity.country, 0.0, 0.0)
        )
    }

    override fun fromDtoToEntity(dto: BikeNetworkDto): BikeNetworkEntity {
        return BikeNetworkEntity(
            id = dto.id ?: "",
            name = dto.name,
            href = dto.href,
            city = dto.location?.city,
            country = dto.location?.country
        )
    }

}

class BikeNetworkDetailRemoteDataMapper @Inject constructor() :
    IModelMapper<BikeNetworkDetailEntity, BikeNetworkDetailResponseDto> {
    override fun fromEntityToDto(entity: BikeNetworkDetailEntity): BikeNetworkDetailResponseDto {
        return BikeNetworkDetailResponseDto(
            network = BikeNetworkDetail(
                id = entity.id,
                name = entity.name,
                href = entity.href,
                location = Location(city = entity.city, country = entity.country, 0.0, 0.0),
                stations = entity.stations?.map {
                    StationDto(
                        id = it.id,
                        freeBikes = it.freeBikes,
                        name = it.name,
                        emptySlot = it.emptySlot,
                        extra = Extra(address = it.address, slots = it.slots)
                    )
                }
            )
        )
    }

    override fun fromDtoToEntity(dto: BikeNetworkDetailResponseDto): BikeNetworkDetailEntity {
        return BikeNetworkDetailEntity(
            id = dto.network?.id ?: "",
            name = dto.network?.name,
            href = dto.network?.href,
            city = dto.network?.location?.city,
            country = dto.network?.location?.country,
            stations = dto.network?.stations?.map {
                Station(
                    id = it.id ?: "",
                    freeBikes = it.freeBikes,
                    name = it.name,
                    emptySlot = it.emptySlot,
                    slots = it.extra.slots,
                    address = it.extra.address
                )
            }
        )
    }

}