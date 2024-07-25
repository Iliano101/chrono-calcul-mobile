package com.iliano.chrono_calcul_mobile.core

import java.time.Duration

fun Duration.toFormatedString(): String {
    val hours = this.toHours()
    val minutes = this.toMinutes() % Constants.MINUTES_IN_HOUR
    return "$hours:$minutes:00"
}