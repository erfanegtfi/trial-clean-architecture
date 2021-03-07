package com.example.trial_clean_architecture.model

import com.example.trial_clean_architecture.ui.common.ListItem
import com.example.trial_clean_architecture.utils.convertGregorianDateTimeToIranianTime
import com.example.trial_clean_architecture.utils.getCurrent_UTC_DateTime
import com.example.trial_clean_architecture.utils.getLocalDateTime
import com.google.gson.annotations.SerializedName


data class ChatContent(
    @SerializedName("message")
    var message: String?,
    @SerializedName("tran_id")
    var tranID: Int?,
    @SerializedName("chat_id")
    var chatID: Int,
    @SerializedName("user_id")
    var userID: Int,
    @SerializedName("status")
    var status: Int,
    @SerializedName("update_time")
    var updateTime: Long
) : ListItem {
    override val listID: String = message!!

    val createTime: String
        get() {
            return convertGregorianDateTimeToIranianTime(
                getLocalDateTime(
//                    getValidDateTime(
                        if(updateTime==0L) getCurrent_UTC_DateTime() else updateTime.toString()
//                    )
                )
            )
        }

    override fun equals(other: Any?): Boolean {
        return (other as ChatContent).message == message
    }

    override fun hashCode(): Int {
        var result = message?.hashCode() ?: 0
        result = 31 * result + (tranID ?: 0)
        result = 31 * result + chatID
        result = 31 * result + userID
        result = 31 * result + status
        result = 31 * result + updateTime.hashCode()
        result = 31 * result + listID.hashCode()
        return result
    }
}
