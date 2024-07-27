package com.iliano.chrono_calcul_mobile.ui.screens.calculator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import com.iliano.chrono_calcul_mobile.core.Constants
import com.iliano.chrono_calcul_mobile.models.Calculation
import java.time.LocalTime

data class CalculatorState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val calculation: Calculation = Calculation(),
    val resultText: String = String(),
    val checkBoxState: Boolean = Constants.DEFAULT_VALUES.OFFSET,
    val timePickerState: TimePickerState = TimePickerState(
        is24Hour = Constants.DEFAULT_VALUES.UI_IS_24_HOUR,
        initialHour = LocalTime.now().hour,
        initialMinute = LocalTime.now().minute
    ),
    val showTimePicker: Boolean = false
)