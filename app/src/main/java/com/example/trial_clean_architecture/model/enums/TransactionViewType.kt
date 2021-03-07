package com.example.trial_clean_architecture.model.enums

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.example.trial_clean_architecture.R

enum class TransactionViewType(val value: Int) {
    Send(1), Receive(2), CashIn(3), CashOut(4), MyRequest(5), HisRequest(6);

    companion object {
        fun transientImageSource(viewType: Int?): Int {
            return when (viewType) {
                CashIn.value -> {
                    R.drawable.ic_cash_in
                }
                CashOut.value -> {
                    R.drawable.ic_cash_out
                }
                else -> {
                    R.drawable.ic_cash_out
                }
            }
        }


        fun transientViewType(viewType: Int?, context: Context, addInName: String, transactionStatus: Int?): Spannable {

            val description = SpannableStringBuilder ()

            when (viewType) {
                Send.value -> {
                    description.append("ارسال به")
                    description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.md_blue_grey_400)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    description.append(" ")
                    val start = description.length
                    description.append(addInName)
                    description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)), start, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                Receive.value -> {
                    description.append("دریافت از")
                    description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.md_blue_grey_400)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    description.append(" ")
                    val start = description.length
                    description.append(addInName)
                    description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)), start, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                CashIn.value -> {
                    description.append("شارژ دایور")
                }
                CashOut.value -> {
                    description.append("برداشت از کیف پول")
                }
                MyRequest.value -> {
                    description.append("درخواست شما از")
                    if (transactionStatus== TransactionStatus.Rejected.value)
                        description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.red)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    if (transactionStatus==TransactionStatus.Completed.value)
                        description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.green)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    description.append(" ")
                    val start = description.length
                    description.append(addInName)
                    description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)), start, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                HisRequest.value -> {
                    description.append("درخواست")
                    if (transactionStatus== TransactionStatus.Rejected.value)
                        description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.red)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    if (transactionStatus==TransactionStatus.Completed.value)
                        description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.green)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    description.append(" ")
                    val start = description.length
                    description.append(addInName)
                    description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)), start, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                else -> {
                    description.append("")
                }
            }

            return description

        }
    }
}