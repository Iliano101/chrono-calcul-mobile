package com.iliano.chrono_calcul_mobile.core

import java.time.LocalTime
import kotlin.time.Duration
import kotlin.time.toKotlinDuration

fun Duration.toFormatedString(): String {
    return Constants.STRINGS.DURATION_FORMAT.format(
        this.inWholeHours,
        this.inWholeMinutes % Constants.MINUTES_IN_HOUR
    )
}

fun LocalTime.toFormatedString(): String {
    return Constants.STRINGS.TIME_FORMAT.format(
        this.hour,
        this.minute
    )
}


fun LocalTime.durationBetween(other: LocalTime): Duration {
    return java.time.Duration.between(this, other).toKotlinDuration()
}