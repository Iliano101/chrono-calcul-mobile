package com.iliano.chrono_calcul_mobile.models

import java.time.Duration
import java.time.LocalTime


data class Calculation(
    private var _targetTime: LocalTime = LocalTime.now()
) {
    fun setTargetTime(hours: Int, minutes: Int) {
        _targetTime = LocalTime.of(hours, minutes)
    }

    val duration: Duration
        get() {
            val currentTime = LocalTime.now()

            if (_targetTime.isBefore(currentTime)) {
                throw Exception("The target time is before the current time")
            }

            return Duration.between(currentTime, _targetTime)
        }

}