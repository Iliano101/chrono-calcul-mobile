package com.iliano.chrono_calcul_mobile.ui.screens.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iliano.chrono_calcul_mobile.core.Constants
import com.iliano.chrono_calcul_mobile.core.toFormatedString
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            while (true) {
                updateResult()
                delay(Constants.UPDATE_DELAY)
            }
        }
    }

    private fun updateResult() {
        TODO("UPDATE")
        _uiState.value.calcul.setTargetTime(23, 0)

        try {
            _uiState.update {
                _uiState.value.copy(
                    displayText = _uiState.value.calcul.duration.toFormatedString()
                )
            }
        } catch (ex: Exception) {
            _uiState.update {
                _uiState.value.copy(
                    displayText = ex.message ?: "Error"
                )
            }
        }
    }
}