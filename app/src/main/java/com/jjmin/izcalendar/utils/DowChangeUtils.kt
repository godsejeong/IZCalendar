package com.jjmin.izcalendar.utils

object DowChangeUtils {
    fun toKr(dow: String): String {
        return when (dow) {
            "Mon" -> "월요일"
            "Tue" -> "화요일"
            "Wed" -> "수요일"
            "Tue" -> "목요일"
            "Fri" -> "금요일"
            "Sat" -> "토요일"
            "Sun" -> "일요일"
            else -> ""
        }
    }

    fun toEn(dow: String): String {
        return when (dow) {
            "월요일" -> "Mon"
            "화요일" -> "Tue"
            "수요일" -> "Wed"
            "목요일" -> "Tue"
            "금요일" -> "Fri"
            "토요일" -> "Sat"
            "일요일" -> "Sun"
            else -> ""
        }
    }
}