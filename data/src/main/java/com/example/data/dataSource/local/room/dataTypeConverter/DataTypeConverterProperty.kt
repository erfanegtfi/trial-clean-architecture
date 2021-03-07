package com.example.data.dataSource.local.room.dataTypeConverter

import androidx.room.TypeConverter
import com.example.data.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataTypeConverterProperty {

//    @TypeConverter
//    fun fromProperty(tracks: PropertyEntity): String {
//        val type = object : TypeToken<PropertyEntity>() {}.type
//        return Gson().toJson(tracks, type)
//    }
//
//    @TypeConverter
//    fun toProperty(track: String): PropertyEntity {
//        val type = object : TypeToken<PropertyEntity>() {}.type
//        return Gson().fromJson<PropertyEntity>(track, type)
//    }

    //////////////////////
    @TypeConverter
    fun fromPropertyValue(tracks: PropertyValueEntity): String {
        val type = object : TypeToken<PropertyValueEntity>() {}.type
        return Gson().toJson(tracks, type)
    }

    @TypeConverter
    fun toPropertyValue(track: String): PropertyValueEntity {
        val type = object : TypeToken<PropertyValueEntity>() {}.type
        return Gson().fromJson<PropertyValueEntity>(track, type)
    }

    /////////////////
//    @TypeConverter
//    fun fromTransaction(tracks: TransactionEntity): String {
//        val type = object : TypeToken<TransactionEntity>() {}.type
//        return Gson().toJson(tracks, type)
//    }
//
//    @TypeConverter
//    fun toTransaction(track: String): TransactionEntity {
//        val type = object : TypeToken<TransactionEntity>() {}.type
//        return Gson().fromJson<TransactionEntity>(track, type)
//    }

//    //////////////////
//    @TypeConverter
//    fun fromUser(tracks: UserEntity): String {
//        val type = object : TypeToken<UserEntity>() {}.type
//        return Gson().toJson(tracks, type)
//    }
//
//    @TypeConverter
//    fun toUser(track: String): UserEntity {
//        val type = object : TypeToken<UserEntity>() {}.type
//        return Gson().fromJson<UserEntity>(track, type)
//    }

//    //////////////////
//    @TypeConverter
//    fun fromUserAvatar(tracks: UserAvatarEntity): String {
//        val type = object : TypeToken<UserAvatarEntity>() {}.type
//        return Gson().toJson(tracks, type)
//    }
//
//    @TypeConverter
//    fun toUserAvatar(track: String): UserAvatarEntity {
//        val type = object : TypeToken<UserAvatarEntity>() {}.type
//        return Gson().fromJson<UserAvatarEntity>(track, type)
//    }


/////////////////////////////////
//    @TypeConverter
//    fun fromUserAvatarList(tracks: ArrayList<UserAvatarEntity>): String {
//        val type = object : TypeToken<ArrayList<UserAvatarEntity>>() {}.type
//        return Gson().toJson(tracks, type)
//    }
//
//    @TypeConverter
//    fun toUserAvatarList(track: String): ArrayList<UserAvatarEntity> {
//        val type = object : TypeToken<ArrayList<UserAvatarEntity>>() {}.type
//        return Gson().fromJson<ArrayList<UserAvatarEntity>>(track, type)
//    }

    ///////////////////////////////
//    @TypeConverter
//    fun fromPropertyList(tracks: ArrayList<PropertyEntity>): String {
//        val type = object : TypeToken<ArrayList<PropertyEntity>>() {}.type
//        return Gson().toJson(tracks, type)
//    }
//
//    @TypeConverter
//    fun toPropertyList(track: String): ArrayList<PropertyEntity> {
//        val type = object : TypeToken<ArrayList<PropertyEntity>>() {}.type
//        return Gson().fromJson<ArrayList<PropertyEntity>>(track, type)
//    }
}