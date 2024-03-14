package com.example.bikeNetwork.data.remotedatasource.modelMapper

import com.example.bikeNetwork.domain.entity.BikeNetworkDetailEntity
import com.example.core.network.dto.BikeNetworkDetail
import com.example.core.network.dto.BikeNetworkDetailResponseDto
import com.example.core.network.dto.Extra
import com.example.core.network.dto.Location
import com.example.core.network.dto.Station
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
                        com.example.bikeNetwork.domain.entity.Station(
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