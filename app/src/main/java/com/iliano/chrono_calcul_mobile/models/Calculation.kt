package com.iliano.chrono_calcul_mobile.models

import java.time.Duration
import java.time.LocalTime

data class Calculation(
    private var _targetTime: LocalTime = LocalTime.now()
) {
    fun setTargetTimeByInt(hours: Int, minutes: Int) {
        _targetTime = LocalTime.of(hours, minutes)
    }

    fun setTargetTimeByLocalTime(localTime: LocalTime) {
        _targetTime = localTime
    }

    val duration: Duration
        get() {
            val currentTime = LocalTime.now()

            if (_targetTime.isBefore(currentTime)) {
                throw Exception()
            }

            return Duration.between(currentTime, _targetTime)
        }

}
