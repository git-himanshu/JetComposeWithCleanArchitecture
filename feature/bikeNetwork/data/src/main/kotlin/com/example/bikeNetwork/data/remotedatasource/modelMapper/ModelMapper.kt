package com.example.bikeNetwork.data.remotedatasource.modelMapper

interface ModelMapper<Entity, Dto> {
    fun fromEntityToDto(entity: Entity): Dto
    fun fromDtoToEntity(dto: Dto): Entity
}