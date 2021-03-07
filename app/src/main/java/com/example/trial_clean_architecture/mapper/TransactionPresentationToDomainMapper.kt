package com.example.trial_clean_architecture.mapper

import com.example.domain.model.*
import com.example.trial_clean_architecture.mapper.base.PresentationLayerMapper
import com.example.trial_clean_architecture.model.*

object TransactionPresentationToDomainMapper : PresentationLayerMapper<TransactionsDomainModel, Transactions> {

    override fun toPresenterModel(e: TransactionsDomainModel): Transactions {
        return Transactions(
            id = e.id,
            properties = e.properties.map {
                Property(
                    code = it.code,
                    propertyID = it.propertyID,
                    tranID = it.tranID,
                    values = PropertyValue(
                        passiveFullName = it.values?.passiveFullName,
                        passivePhone = it.values?.passivePhone,
                        userFullName = it.values?.userFullName,
                        userPhone = it.values?.userPhone,
                    )
                )
            },
            transaction = Transaction(
                creationTime = e.transaction?.creationTime,
                amount = e.transaction?.amount,
                status = e.transaction?.status,
                tranID = e.transaction?.tranID,
                updateTime = e.transaction?.updateTime,
                description = e.transaction?.description,
                userID = e.transaction?.userID,
                viewType = e.transaction?.viewType,
            ),
            user = User(
                aboutMe = e.user?.aboutMe,
                firstName = e.user?.firstName,
                lastName = e.user?.lastName,
                phone = e.user?.phone,
                userID = e.user?.userID,
                username = e.user?.username
            ),
            userAvatars = e.userAvatars?.map {
                UserAvatar(
                    avatarID = it.avatarID,
                    updateTime = it.updateTime,
                    url = it.url,
                    userID = it.userID
                )
            },
            chat = Chat(
                chatID = e.chat?.chatID,
                updateTime = e.chat?.updateTime,
                message = e.chat?.message,
                tranID = e.chat?.tranID,
                status = e.chat?.status,
                userID = e.chat?.userID
            )
        )
    }

    override fun toDomain(d: Transactions): TransactionsDomainModel {
        return TransactionsDomainModel(
            id = d.id,
            properties = d.properties.map {
                PropertyDomainModel(
                    code = it.code,
                    propertyID = it.propertyID,
                    tranID = it.tranID,
                    values = PropertyValueDomainModel(
                        passiveFullName = it.values?.passiveFullName,
                        passivePhone = it.values?.passivePhone,
                        userFullName = it.values?.userFullName,
                        userPhone = it.values?.userPhone,
                    )
                )
            },
            transaction = TransactionDomainModel(
                creationTime = d.transaction?.creationTime,
                amount = d.transaction?.amount,
                status = d.transaction?.status,
                tranID = d.transaction?.tranID,
                updateTime = d.transaction?.updateTime,
                description = d.transaction?.description,
                userID = d.transaction?.userID,
                viewType = d.transaction?.viewType,
            ),
            user = UserDomainModel(
                aboutMe = d.user?.aboutMe,
                firstName = d.user?.firstName,
                lastName = d.user?.lastName,
                phone = d.user?.phone,
                userID = d.user?.userID,
                username = d.user?.username
            ),
            userAvatars = d.userAvatars?.map {
                UserAvatarDomainModel(
                    avatarID = it.avatarID,
                    updateTime = it.updateTime,
                    url = it.url,
                    userID = it.userID
                )
            },
            chat = ChatDomainModel(
                chatID = d.chat?.chatID,
                updateTime = d.chat?.updateTime,
                message = d.chat?.message,
                tranID = d.chat?.tranID,
                status = d.chat?.status,
                userID = d.chat?.userID
            )
        )
    }

}