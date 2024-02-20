package com.example.bikenetwork.data.remotedatasource.model_mapper


import com.example.bikenetwork.data.dto.BikeNetworkDetail
import com.example.bikenetwork.data.dto.BikeNetworkDetailResponseDto
import com.example.bikenetwork.data.dto.BikeNetworkDto
import com.example.bikenetwork.data.dto.Extra
import com.example.bikenetwork.data.dto.Location
import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.entity.BikeNetworkEntity
import com.example.bikenetwork.domain.entity.Station
import javax.inject.Inject
import com.example.bikenetwork.data.dto.Station as StationDto


class BikeNetworkRemoteListDataMapper @Inject constructor() :
    IModelMapper<BikeNetworkEntity, BikeNetworkDto> {
    override fun fromEntityToDto(entity: BikeNetworkEntity): BikeNetworkDto {
        return BikeNetworkDto(
            id = entity.id,
            name = entity.name,
            href = entity.href,
            Location(
                city = entity.city,
                country = entity.country,
                0.0,
                0.0
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

class BikeNetworkDetailRemoteDataMapper @Inject constructor() :
    IModelMapper<BikeNetworkDetailEntity, BikeNetworkDetailResponseDto> {
    override fun fromEntityToDto(entity: BikeNetworkDetailEntity): BikeNetworkDetailResponseDto {
        return BikeNetworkDetailResponseDto(
            network = BikeNetworkDetail(
                id = entity.id,
                name = entity.name,
                href = entity.href,
                location = Location(
                    city = entity.city,
                    country = entity.country,
                    0.0,
                    0.0
                ),
                stations = entity.stations.map {
                    StationDto(
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
            href = dto.network?.href ?: "",
            city = dto.network?.location?.city ?: "",
            country = dto.network?.location?.country ?: "",
            stations = dto.network?.stations?.let { stationList ->
                stationList.map {
                    Station(
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