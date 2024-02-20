package com.example.bikenetwork.data.remotedatasource.model_mapper

interface IModelMapper<Entity,Dto> {
    fun fromEntityToDto(entity: Entity): Dto
    fun fromDtoToEntity(dto: Dto): Entity
}