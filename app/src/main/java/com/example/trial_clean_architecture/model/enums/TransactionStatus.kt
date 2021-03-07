package com.example.trial_clean_architecture.model.enums

import com.example.trial_clean_architecture.R

enum class TransactionStatus(val value: Int) {
    New(0), Completed(1), Pending(2), Rejected(3);

    companion object {
        fun transientStatusTitle(transactionStatus: Int?): String {
            return when (transactionStatus) {
                New.value -> {
                    "جدید"
                }
                Completed.value -> {
                    "انجام شد"
                }
                Pending.value -> {
                    "بررسی"
                }
                Rejected.value -> {
                    "رد شد"
                }
                else -> {
                    ""
                }
            }
        }

        fun transientStatusBackground(transactionStatus: Int?): Int {
            return when (transactionStatus) {
                New.value -> {
                    R.drawable.bg_rectangle_grey_radius
                }
                Completed.value -> {
                    R.drawable.bg_rectangle_green_radius
                }
                Pending.value -> {
                    R.drawable.bg_rectangle_amber_radius
                }
                Rejected.value -> {
                    R.drawable.bg_rectangle_red_radius
                }
                else -> {
                    R.drawable.bg_rectangle_grey_radius
                }
            }
        }
    }
}