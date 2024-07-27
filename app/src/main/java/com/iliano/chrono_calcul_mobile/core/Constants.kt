package com.iliano.chrono_calcul_mobile.core

object Constants {
    const val UPDATE_DELAY = 1000L

    const val MINUTES_IN_HOUR = 60

    object DEFAULT_VALUES {
        const val OFFSET = true
        const val UI_IS_24_HOUR = true
        const val TIME_PICKER_SHOWN = false
    }

    object TIME_OFFSETS {
        const val SECURITY_MINUTES = 15L
    }

    object STRINGS {
        const val DEFAULT_DISPLAY = "00:00:00"
        const val DURATION_FORMAT = "%02d:%02d:00"
        const val TIME_FORMAT = "%02d:%02d"
    }
}