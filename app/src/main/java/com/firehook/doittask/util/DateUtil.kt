package com.firehook.doittask.util

import java.text.SimpleDateFormat

/**
 * Created by Vladyslav Bondar on 31.07.2019
 * Skype: diginital
 */

class DateUtil {

    companion object {
        fun convertDate(longDate: Long): String {
            val dateFormat = SimpleDateFormat("dd/MM/yy")
            return dateFormat.format(longDate)
        }
    }
}