package com.example.trial_clean_architecture.utils

import java.text.DecimalFormat
import java.text.NumberFormat


object Utils {

    fun priceFormatter(price: Long?): String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        return price.let {
            formatter.format(it)
        } ?: "0"
    }

}


