package com.example.data.mapper.base

import com.example.data.models.BaseEntity
import com.example.domain.model.base.BaseDomainModel

interface DataLayerMapper<A : BaseEntity?, B : BaseDomainModel?> {
    fun toDomainModel(e: A): B
    fun toEntity(d: B): A
}
