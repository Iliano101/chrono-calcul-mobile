package com.iliano.chrono_calcul_mobile.ui.screens.calculator

import com.iliano.chrono_calcul_mobile.core.Constants
import com.iliano.chrono_calcul_mobile.models.Calculation

data class CalculatorState(
    val calculation: Calculation = Calculation(),
    val resultText: String = String(),
    val checkBoxState: Boolean = Constants.DEFAULT_VALUES.OFFSET
)