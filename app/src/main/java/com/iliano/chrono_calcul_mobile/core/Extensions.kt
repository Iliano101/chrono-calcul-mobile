package com.iliano.chrono_calcul_mobile.core

import java.time.Duration
import java.time.LocalTime

fun Duration.toFormatedString(): String {
    return Constants.STRINGS.DURATION_FORMAT.format(
        this.toHours(),
        this.toMinutes() % Constants.MINUTES_IN_HOUR
    )
}

fun LocalTime.toFormatedString(): String {

    return Constants.STRINGS.TIME_FORMAT.format(
        this.hour,
        this.minute
    )
}