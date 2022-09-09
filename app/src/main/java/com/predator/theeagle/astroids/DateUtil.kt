package com.predator.theeagle.astroids

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun todayDate(): String {
        val simpleDate = SimpleDateFormat("yyyy-MM-dd")
        return simpleDate.format(Date())
    }
}