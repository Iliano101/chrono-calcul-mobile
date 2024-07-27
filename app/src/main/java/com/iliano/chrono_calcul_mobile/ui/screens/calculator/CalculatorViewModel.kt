@file:OptIn(ExperimentalMaterial3Api::class)

package com.iliano.chrono_calcul_mobile.ui.screens.calculator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iliano.chrono_calcul_mobile.core.Constants
import com.iliano.chrono_calcul_mobile.core.toFormatedString
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration

class CalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorState())
    val uiState = _uiState.asStateFlow()

    private val _eventsFlow = MutableSharedFlow<ScreenEvent>()
    val eventsFlow = _eventsFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            while (true) {
                updateTextResult()
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
        updateTextResult()
    }

    fun showTimePicker() {
        _uiState.update {
            _uiState.value.copy(
                showTimePicker = true
            )
        }
    }

    fun hideTimePicker() {
        viewModelScope.launch {
            _uiState.update {
                _uiState.value.copy(
                    showTimePicker = false
                )
            }
            val timePickerState = _uiState.value.timePickerState
            _uiState.value.calculation.setTargetTime(timePickerState.hour, timePickerState.minute)

            if (_uiState.value.calculation.duration == Duration.ZERO) {
                _eventsFlow.emit(ScreenEvent.EnteredTimeInvalid)
            }
            updateTextResult()
        }
    }

    private fun setCalculationOffset(enable: Boolean) {
        _uiState.value.calculation.applyOffset = enable
    }

    private fun updateTextResult() {
        _uiState.update {
            _uiState.value.copy(
                resultText = _uiState.value.calculation.duration.toFormatedString()
            )
        }
    }

    sealed class ScreenEvent {
        data object EnteredTimeInvalid : ScreenEvent()
        data object GenericError : ScreenEvent()
    }
}