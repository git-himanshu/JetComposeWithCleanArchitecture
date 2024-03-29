package com.example.data.remotedatasource.modelMapper

import com.example.data.dto.BikeNetworkDetail
import com.example.data.dto.BikeNetworkDetailResponseDto
import com.example.data.dto.Extra
import com.example.data.dto.Location
import com.example.data.dto.Station
import com.example.domain.entity.BikeNetworkDetailEntity
import javax.inject.Inject

class BikeNetworkDetailRemoteDataMapper @Inject constructor() :
    ModelMapper<BikeNetworkDetailEntity, BikeNetworkDetailResponseDto> {
    override fun fromEntityToDto(entity: BikeNetworkDetailEntity): BikeNetworkDetailResponseDto {
        return BikeNetworkDetailResponseDto(
            network = BikeNetworkDetail(
                id = entity.id,
                name = entity.name,
                location = Location(
                    city = entity.city,
                    country = entity.country
                ),
                stations = entity.stations.map {
                    Station(
                        id = it.id,
                        freeBikes = it.freeBikes,
                        name = it.name,
                        emptySlot = it.emptySlot,
                        extra = Extra(
                            address = it.address,
                            slots = it.slots
                        )
                    )
                }
            )
        )
    }

    override fun fromDtoToEntity(dto: BikeNetworkDetailResponseDto): BikeNetworkDetailEntity {
        return BikeNetworkDetailEntity(
            id = dto.network?.id ?: "",
            name = dto.network?.name ?: "",
            city = dto.network?.location?.city ?: "",
            country = dto.network?.location?.country ?: "",
            stations = dto.network?.stations?.let { stations ->
                stations.map {
                    com.example.domain.entity.Station(
                        id = it.id ?: "",
                        freeBikes = it.freeBikes ?: 0,
                        name = it.name ?: "",
                        emptySlot = it.emptySlot ?: 0,
                        slots = it.extra.slots ?: 0,
                        address = it.extra.address ?: ""
                    )
                }
            } ?: emptyList()
        )
    }

}