package com.example.data.dataSource.local.room.dataTypeConverter

import androidx.room.TypeConverter
import com.example.data.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataTypeConverterTransaction {

    /////////////////////////////// PropertyEntity /////////////////////////////////////
    @TypeConverter
    fun fromProperty(entity: PropertyEntity): String {
        val type = object : TypeToken<PropertyEntity>() {}.type
        return Gson().toJson(entity, type)
    }

    @TypeConverter
    fun toProperty(entity: String): PropertyEntity {
        val type = object : TypeToken<PropertyEntity>() {}.type
        return Gson().fromJson<PropertyEntity>(entity, type)
    }

    /////////////////////////////// ChatEntity /////////////////////////////////////
    @TypeConverter
    fun fromChat(entity: ChatEntity?): String {
        val type = object : TypeToken<ChatEntity>() {}.type
        return Gson().toJson(entity, type)
    }

    @TypeConverter
    fun toChat(entity: String): ChatEntity? {
        val type = object : TypeToken<ChatEntity>() {}.type
        return Gson().fromJson<ChatEntity>(entity, type)
    }

    /////////////////////////////// PropertyValueEntity /////////////////////////////////////
    @TypeConverter
    fun fromPropertyValue(entity: PropertyValueEntity): String {
        val type = object : TypeToken<PropertyValueEntity>() {}.type
        return Gson().toJson(entity, type)
    }

    @TypeConverter
    fun toPropertyValue(entity: String): PropertyValueEntity {
        val type = object : TypeToken<PropertyValueEntity>() {}.type
        return Gson().fromJson<PropertyValueEntity>(entity, type)
    }

    /////////////////////////////// TransactionEntity /////////////////////////////////////
    @TypeConverter
    fun fromTransaction(entity: TransactionEntity): String {
        val type = object : TypeToken<TransactionEntity>() {}.type
        return Gson().toJson(entity, type)
    }

    @TypeConverter
    fun toTransaction(entity: String): TransactionEntity {
        val type = object : TypeToken<TransactionEntity>() {}.type
        return Gson().fromJson<TransactionEntity>(entity, type)
    }

    /////////////////////////////// UserEntity /////////////////////////////////////
    @TypeConverter
    fun fromUser(entity: UserEntity? = null): String? {
        val type = object : TypeToken<UserEntity>() {}.type
        return Gson().toJson(entity, type)
    }

    @TypeConverter
    fun toUser(entity: String? = null): UserEntity? {
        val type = object : TypeToken<UserEntity>() {}.type
        return Gson().fromJson<UserEntity>(entity, type)
    }

    /////////////////////////////// UserAvatarEntity /////////////////////////////////////
    @TypeConverter
    fun fromUserAvatar(entity: UserAvatarEntity? = null): String? {
        val type = object : TypeToken<UserAvatarEntity>() {}.type
        return Gson().toJson(entity, type)
    }

    @TypeConverter
    fun toUserAvatar(entity: String? = null): UserAvatarEntity? {
        val type = object : TypeToken<UserAvatarEntity>() {}.type
        return Gson().fromJson<UserAvatarEntity>(entity, type)
    }


    /////////////////////////////// UserAvatarEntity List /////////////////////////////////////
    @TypeConverter
    fun fromUserAvatarList(entityList: List<UserAvatarEntity>?): String? {
        val type = object : TypeToken<List<UserAvatarEntity>>() {}.type
        return Gson().toJson(entityList, type)
    }

    @TypeConverter
    fun toUserAvatarList(entity: String?): List<UserAvatarEntity>? {
        val type = object : TypeToken<List<UserAvatarEntity>>() {}.type
        return Gson().fromJson<List<UserAvatarEntity>>(entity, type)
    }

    /////////////////////////////// PropertyEntity List /////////////////////////////////////
    @TypeConverter
    fun fromPropertyList(entityList: List<PropertyEntity>): String {
        val type = object : TypeToken<List<PropertyEntity>>() {}.type
        return Gson().toJson(entityList, type)
    }

    @TypeConverter
    fun toPropertyList(entity: String): List<PropertyEntity> {
        val type = object : TypeToken<List<PropertyEntity>>() {}.type
        return Gson().fromJson<List<PropertyEntity>>(entity, type)
    }
}