package com.example.data.models

import androidx.room.*
import com.example.data.dataSource.local.room.dataTypeConverter.DataTypeConverterTransaction
import com.example.data.models.TransactionsEntity.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME, indices = [Index(value = ["transaction"], unique = true)])
@TypeConverters(DataTypeConverterTransaction::class)
data class TransactionsEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int,
    @ColumnInfo(name = "properties")
    @SerializedName("properties")
    var properties: List<PropertyEntity> = ArrayList(),
    @SerializedName("transaction")
    var transaction: TransactionEntity?,
    @SerializedName("user")
    @Embedded
    var user: UserEntity?,
    @ColumnInfo(name = "user_avatars")
    @SerializedName("user_avatars")
    var userAvatars: List<UserAvatarEntity>? = ArrayList(),
    @SerializedName("chat")
    var chat: ChatEntity?,
    ) : BaseEntity {

    companion object {
        const val TABLE_NAME = "transaction_list"
    }

}

data class ChatEntity(
    @SerializedName("chat_id")
    var chatID: Int?,
    @SerializedName("update_time")
    var updateTime: Long?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("tran_id")
    var tranID: Int?,
    @SerializedName("status")
    var status: Int?,
    @SerializedName("user_id")
    var userID: Int?
) : BaseEntity

data class PropertyEntity(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("property_id")
    var propertyID: Int?,
    @SerializedName("tran_id")
    var tranID: Int?,
    @SerializedName("values")
    var values: PropertyValueEntity?
) : BaseEntity

data class PropertyValueEntity(
    @SerializedName("passive_fullname")
    var passiveFullName: String?,
    @SerializedName("passive_phone")
    var passivePhone: String?,
    @SerializedName("user_fullname")
    var userFullName: String?,
    @SerializedName("user_phone")
    var userPhone: String?
) : BaseEntity

data class TransactionEntity(
    @SerializedName("creation_time")
    var creationTime: Long?,
    @SerializedName("amount")
    var amount: Double?,
    @SerializedName("status")
    var status: Int?,
    @SerializedName("tran_id")
    var tranID: Int?,
    @SerializedName("update_time")
    var updateTime: Long?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("user_id")
    var userID: Int?,
    @SerializedName("view_type")
    var viewType: Int?
) : BaseEntity

data class UserEntity(
    @Ignore
    @SerializedName("about_me")
    var aboutMe: String?,
    @ColumnInfo(name = "firstName")
    @SerializedName("first_name")
    var firstName: String?,
    @ColumnInfo(name = "lastName")
    @SerializedName("last_name")
    var lastName: String?,
    @Ignore
    @SerializedName("phone")
    var phone: String?,
    @Ignore
    @SerializedName("user_id")
    var userID: Int?,
    @Ignore
    @SerializedName("username")
    var username: String?
) : BaseEntity{
    constructor() : this("","","","",0,"")
}

data class UserAvatarEntity(
    @SerializedName("avatar_id")
    var avatarID: Int?,
    @SerializedName("update_time")
    var updateTime: Long?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("user_id")
    var userID: Int?
) : BaseEntity