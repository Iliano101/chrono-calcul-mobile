package com.iliano.chrono_calcul_mobile.ui.screens.calculator

import com.iliano.chrono_calcul_mobile.models.Calculation

data class CalculatorState(
    val calcul: Calculation = Calculation(),
    val displayText: String = ""
)