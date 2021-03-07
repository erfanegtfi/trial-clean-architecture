package com.example.data.mapper

import com.example.data.mapper.base.DataLayerMapper
import com.example.data.models.*
import com.example.domain.model.*

object TransactionEntityToDomainModelMapper : DataLayerMapper<TransactionsEntity, TransactionsDomainModel> {
    override fun toDomainModel(e: TransactionsEntity): TransactionsDomainModel {
        return TransactionsDomainModel(
            id = e.id,
            properties = e.properties.map {
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
                creationTime = e.transaction?.creationTime,
                amount = e.transaction?.amount,
                status = e.transaction?.status,
                tranID = e.transaction?.tranID,
                updateTime = e.transaction?.updateTime,
                description = e.transaction?.description,
                userID = e.transaction?.userID,
                viewType = e.transaction?.viewType,
            ),
            user = UserDomainModel(
                aboutMe = e.user?.aboutMe,
                firstName = e.user?.firstName,
                lastName = e.user?.lastName,
                phone = e.user?.phone,
                userID = e.user?.userID,
                username = e.user?.username
            ),
            userAvatars = e.userAvatars?.map {
                UserAvatarDomainModel(
                    avatarID = it.avatarID,
                    updateTime = it.updateTime,
                    url = it.url,
                    userID = it.userID
                )
            },
            chat = ChatDomainModel(
                chatID = e.chat?.chatID,
                updateTime = e.chat?.updateTime,
                message = e.chat?.message,
                tranID = e.chat?.tranID,
                status = e.chat?.status,
                userID = e.chat?.userID
            )

        )
    }

    override fun toEntity(d: TransactionsDomainModel): TransactionsEntity {
        return TransactionsEntity(
            id = d.id,
            properties = d.properties.map {
                PropertyEntity(
                    code = it.code,
                    propertyID = it.propertyID,
                    tranID = it.tranID,
                    values = PropertyValueEntity(
                        passiveFullName = it.values?.passiveFullName,
                        passivePhone = it.values?.passivePhone,
                        userFullName = it.values?.userFullName,
                        userPhone = it.values?.userPhone,
                    )
                )
            },
            transaction = TransactionEntity(
                creationTime = d.transaction?.creationTime,
                amount = d.transaction?.amount,
                status = d.transaction?.status,
                tranID = d.transaction?.tranID,
                updateTime = d.transaction?.updateTime,
                description = d.transaction?.description,
                userID = d.transaction?.userID,
                viewType = d.transaction?.viewType,
            ),
            user = UserEntity(
                aboutMe = d.user?.aboutMe,
                firstName = d.user?.firstName,
                lastName = d.user?.lastName,
                phone = d.user?.phone,
                userID = d.user?.userID,
                username = d.user?.username
            ),
            userAvatars = d.userAvatars?.map {
                UserAvatarEntity(
                    avatarID = it.avatarID,
                    updateTime = it.updateTime,
                    url = it.url,
                    userID = it.userID
                )
            },
            chat = ChatEntity(
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