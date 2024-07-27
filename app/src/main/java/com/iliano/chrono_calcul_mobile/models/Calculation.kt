package com.iliano.chrono_calcul_mobile.models

import com.iliano.chrono_calcul_mobile.core.Constants
import java.time.Duration
import java.time.LocalTime

data class Calculation(
    var applyOffset: Boolean = Constants.DEFAULT_VALUES.OFFSET,
    private var _targetTime: LocalTime = LocalTime.now()
) {


    fun setTargetTime(hours: Int, minutes: Int) {
        _targetTime = LocalTime.of(hours, minutes)
    }

    fun getTargetTime(): LocalTime {
        return _targetTime
    }

    val duration: Duration
        get() {
            var currentTime = LocalTime.now()

            if (applyOffset) {
                currentTime = currentTime.plusMinutes(Constants.TIME_OFFSETS.SECURITY_MINUTES)
            }

            if (_targetTime.isBefore(currentTime)) {
                throw Exception("Le temps entré est dans le passé")
            }


            return Duration.between(currentTime, _targetTime)
        }

}
