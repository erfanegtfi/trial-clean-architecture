package com.example.trial_clean_architecture.mapper.base

import com.example.domain.model.base.BaseDomainModel
import com.example.trial_clean_architecture.model.base.BasePresentationModel


interface PresentationLayerMapper<A : BaseDomainModel?, B : BasePresentationModel?> {
    fun toPresenterModel(e: A): B
    fun toDomain(d: B): A
}
