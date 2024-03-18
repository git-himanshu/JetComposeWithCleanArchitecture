package com.example.data.remotedatasource.modelMapper

interface ModelMapper<Entity, Dto> {
    fun fromEntityToDto(entity: Entity): Dto
    fun fromDtoToEntity(dto: Dto): Entity
}