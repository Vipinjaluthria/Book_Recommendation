package com.example.plutoacademy.service.utility

import java.text.SimpleDateFormat


public class DateUtils {
    companion object {
        public fun convertToDateString(date: String): String {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'").parse(date))
        }
    }
}