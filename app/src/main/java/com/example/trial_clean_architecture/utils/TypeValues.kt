package com.example.trial_clean_architecture.utils

//fun transientViewType(viewType: Int?, context: Context, addInName: String, transactionStatus: Int?): Spannable {
//
//    val description = SpannableStringBuilder ()
//
//    when (viewType) {
//        TransactionViewType.Send.value -> {
//            description.append("ارسال به")
//            description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.md_blue_grey_400)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            description.append(" ")
//            val start = description.length
//            description.append(addInName)
//            description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)), start, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        TransactionViewType.Receive.value -> {
//            description.append("دریافت از")
//            description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.md_blue_grey_400)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            description.append(" ")
//            val start = description.length
//            description.append(addInName)
//            description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)), start, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        TransactionViewType.CashIn.value -> {
//            description.append("شارژ دایور")
//        }
//        TransactionViewType.CashOut.value -> {
//            description.append("برداشت از کیف پول")
//        }
//        TransactionViewType.MyRequest.value -> {
//            description.append("درخواست شما از")
//            if (transactionStatus== TransactionStatus.Rejected.value)
//                description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.red)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            if (transactionStatus==TransactionStatus.Completed.value)
//                description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.green)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            description.append(" ")
//            val start = description.length
//            description.append(addInName)
//            description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)), start, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        TransactionViewType.HisRequest.value -> {
//            description.append("درخواست")
//            if (transactionStatus== TransactionStatus.Rejected.value)
//                description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.red)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            if (transactionStatus==TransactionStatus.Completed.value)
//                description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.green)), 0, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            description.append(" ")
//            val start = description.length
//            description.append(addInName)
//            description.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)), start, description.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        else -> {
//            description.append("")
//        }
//    }
//
//    return description
//
//}

//fun transientStatusTitle(transactionStatus: Int?): String {
//    return when (transactionStatus) {
//        TransactionStatus.New.value -> {
//            "جدید"
//        }
//        TransactionStatus.Completed.value -> {
//            "انجام شد"
//        }
//        TransactionStatus.Pending.value -> {
//           "بررسی"
//        }
//        TransactionStatus.Rejected.value -> {
//            "رد شد"
//        }
//        else -> {
//            ""
//        }
//    }
//}

//fun transientStatusColor(transactionStatus: Int?): Int {
//    return when (transactionStatus) {
//        TransactionStatus.New.value -> {
//           R.color.md_grey_500
//        }
//        TransactionStatus.Completed.value -> {
//            R.color.md_green_500
//        }
//        TransactionStatus.Pending.value -> {
//            R.color.md_amber_500
//        }
//        TransactionStatus.Rejected.value -> {
//            R.color.md_red_500
//        }
//        else -> {
//            R.color.md_grey_500
//        }
//    }
//}

//fun transientImageSource(viewType: Int?): Int {
//    return when (viewType) {
//        TransactionViewType.CashIn.value -> {
//            R.drawable.ic_cash_in
//        }
//        TransactionViewType.CashOut.value -> {
//            R.drawable.ic_cash_out
//        }
//        else -> {
//            R.drawable.ic_cash_out
//        }
//    }
//}

//mapOf<Int, Int>(
//3 to  R.drawable.ic_cash_in,
//4 to  R.drawable.ic_cash_out,
//)

//val transientViewType = mapOf(
//    1 to "ارسال مبلغ از طرف شما به شخص دیگر",
//    2 to "دریافت مبلغ از شخص دیگر",
//    3 to "شارژ دایور (شارژ کیف پول دایور)",
//    4 to "برداشت از کیف پول و انتقال آن به یک کارت دیگر",
//    5 to "درخواست مبلغی از طرف شما ب شخص دیگر",
//    6 to "درخواست شخص دیگری از شما برای دریافت مبلغی",
//)