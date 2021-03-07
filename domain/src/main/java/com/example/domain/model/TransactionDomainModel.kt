package com.example.domain.model

import com.example.domain.model.base.BaseDomainModel
import com.google.gson.annotations.SerializedName


data class TransactionsDomainModel(
    val id: Int,
    var properties: List<PropertyDomainModel> = ArrayList(),
    val transaction: TransactionDomainModel?,
    val user: UserDomainModel?,
    var userAvatars: List<UserAvatarDomainModel>?,
    val chat: ChatDomainModel?,
    ) : BaseDomainModel

data class ChatDomainModel(
    var chatID: Int?,
    var updateTime: Long?,
    var message: String?,
    var tranID: Int?,
    var status: Int?,
    var userID: Int?
) : BaseDomainModel

data class PropertyDomainModel(
    val code: Int?,
    val propertyID: Int?,
    val tranID: Int?,
    val values: PropertyValueDomainModel?
) : BaseDomainModel

data class PropertyValueDomainModel(
    val passiveFullName: String?,
    val passivePhone: String?,
    val userFullName: String?,
    val userPhone: String?
) : BaseDomainModel

data class TransactionDomainModel(
    val creationTime: Long?,
    val amount: Double?,
    val status: Int?,
    val tranID: Int?,
    val updateTime: Long?,
    var description: String?,
    val userID: Int?,
    val viewType: Int?
) : BaseDomainModel

data class UserDomainModel(
    val aboutMe: String?,
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val userID: Int?,
    val username: String?
) : BaseDomainModel

data class UserAvatarDomainModel(
    val avatarID: Int?,
    val updateTime: Long?,
    val url: String?,
    val userID: Int?
) : BaseDomainModel