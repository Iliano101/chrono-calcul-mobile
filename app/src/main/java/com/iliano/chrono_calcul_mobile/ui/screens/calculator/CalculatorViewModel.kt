@file:OptIn(ExperimentalMaterial3Api::class)

package com.iliano.chrono_calcul_mobile.ui.screens.calculator

import androidx.compose.material3.ExperimentalMaterial3Api
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

    fun onOffsetToggle(enable: Boolean) {
        _uiState.update {
            _uiState.value.copy(
                checkBoxState = enable
            )
        }
        setCalculationOffset(enable)
        updateResult()
    }

    fun showTimePicker() {
        _uiState.update {
            _uiState.value.copy(
                showTimePicker = true
            )
        }
    }

    fun hideTimePicker() {
        _uiState.update {
            _uiState.value.copy(
                showTimePicker = false
            )
        }
        val timePickerState = _uiState.value.timePickerState
        _uiState.value.calculation.setTargetTime(timePickerState.hour, timePickerState.minute)

        updateResult()
    }

    private fun setCalculationOffset(enable: Boolean) {
        _uiState.value.calculation.applyOffset = enable
    }

    private fun updateResult() {
        _uiState.update {
            _uiState.value.copy(
                resultText = try {
                    _uiState.value.calculation.duration.toFormatedString()
                } catch (_: Exception) {
                    Constants.STRINGS.DEFAULT_DISPLAY
                }
            )
        }
    }
}