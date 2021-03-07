package com.example.trial_clean_architecture.model

import com.example.trial_clean_architecture.R
import com.example.trial_clean_architecture.model.base.BasePresentationModel
import com.example.trial_clean_architecture.model.enums.TransactionStatus.Companion.transientStatusBackground
import com.example.trial_clean_architecture.model.enums.TransactionStatus.Companion.transientStatusTitle
import com.example.trial_clean_architecture.model.enums.TransactionViewType
import com.example.trial_clean_architecture.model.enums.TransactionViewType.Companion.transientImageSource
import com.example.trial_clean_architecture.ui.common.ListItem
import com.example.trial_clean_architecture.utils.*
import kotlin.collections.ArrayList

data class Transactions(
    val id: Int,
    var properties: List<Property> = ArrayList(),
    val transaction: Transaction?,
    val user: User?,
    var userAvatars: List<UserAvatar>?,
    val chat: Chat?,

    ) : BasePresentationModel, ListItem {
    override val listID: String = properties[0].propertyID.toString()

    val userAvatar: String
        get() {
            return userAvatars?.getOrNull(0)?.url ?: ""
        }

    val userFullName: String
        get() {
            return user?.firstName + " " + user?.lastName
        }

    val hasChat: Boolean
        get() {
            return chat?.chatID != null
        }
}

data class Chat(
    var chatID: Int?,
    var updateTime: Long?,
    var message: String?,
    var tranID: Int?,
    var status: Int?,
    var userID: Int?
) : BasePresentationModel


data class Property(
    val code: Int?,
    val propertyID: Int?,
    val tranID: Int?,
    val values: PropertyValue?
) : BasePresentationModel

data class PropertyValue(
    val passiveFullName: String? = "",
    val passivePhone: String? = "",
    val userFullName: String? = "",
    val userPhone: String? = ""
) : BasePresentationModel

data class Transaction(
    val creationTime: Long?,
    val amount: Double?,
    val status: Int?,
    val tranID: Int?,
    val updateTime: Long?,
    val description: String? = "",
    val userID: Int?,
    val viewType: Int?
) : BasePresentationModel {

//    val viewTypeTitle: String
//        get() {
//            return transientViewType(viewType)
//        }

    val imageSource: Int
        get() {
            return transientImageSource(viewType)
        }

    val statusTitle: String
        get() {
            return transientStatusTitle(status)
        }

    val statusBackground: Int
        get() {
            return transientStatusBackground(status)
        }

    val amountFormatter: String
        get() {
            return (if (amountReceive) "+" else "") + Utils.priceFormatter(amount?.toLong())
        }


    val createDateTime: String
        get() {
            return convertGregorianDateTimeToIranianDateTime(
                getLocalDateTime(
                    if(updateTime==0L) getCurrent_UTC_DateTime() else updateTime.toString()
                )
            )
        }

    val amountReceive: Boolean
        get() {
            return (viewType == TransactionViewType.Receive.value || viewType == TransactionViewType.MyRequest.value) //&& status == TransactionStatus.Completed.value
        }

    val amountColor: Int
        get() {
            return if (amountReceive)
                R.color.green
            else
                R.color.black
        }

}

data class User(
    val aboutMe: String?,
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val userID: Int?,
    val username: String?
) : BasePresentationModel

data class UserAvatar(
    val avatarID: Int?,
    val updateTime: Long?,
    val url: String?,
    val userID: Int?
) : BasePresentationModel