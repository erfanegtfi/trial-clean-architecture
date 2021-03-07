package com.example.trial_clean_architecture.utils

import java.text.SimpleDateFormat
import java.util.*

//fun getValidDateTime(dateStr: String): String {
//    var date = ""
//    date = if (dateStr.length >= 14)
//        dateStr
//    else
//        getCurrent_UTC_DateTime()
//
//    val year: String = date.substring(0, 4)
//    val mouth: String = date.substring(4, 6)
//    val day: String = date.substring(6, 8)
//    val hour: String = date.substring(8, 10)
//    val min: String = date.substring(10, 12)
//    val second: String = date.substring(12, 14)
//
//    return "$day $mouth $year $hour:$min"
//}

val df = SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH)

fun getCurrent_UTC_DateTime(): String {
    df.timeZone = TimeZone.getTimeZone("UTC")
    return df.format(Date())
}

fun getLocalDateTime(dateStr: String): String {
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date: Date = df.parse(dateStr)
    df.timeZone = TimeZone.getDefault()
    return df.format(date)
}


fun convertGregorianDateTimeToIranianDateTime(dateStr: String): String {
//    val df = SimpleDateFormat("dd MM yyyy HH:mm", Locale.ENGLISH)
    val date: Date = df.parse(dateStr)
    val cal = CalendarTool()
    cal.setGregorianDate(date)
    Calendar.getInstance().time = date
    val hour: Int = Calendar.getInstance().get(Calendar.HOUR)
    val min: Int = Calendar.getInstance().get(Calendar.MINUTE)
    return "${cal.iranianDay} ${cal.iranianMonthStr} ${cal.iranianYear} $hour:$min"
}

fun convertGregorianDateTimeToIranianTime(dateStr: String): String {
//    val df = SimpleDateFormat("dd MM yyyy HH:mm", Locale.ENGLISH)
    val date: Date = df.parse(dateStr)
    val dfTime = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    return dfTime.format(date)
}