package com.example.bikeNetwork.data.remotedatasource.modelMapper

interface IModelMapper<Entity, Dto> {
    fun fromEntityToDto(entity: Entity): Dto
    fun fromDtoToEntity(dto: Dto): Entity
}