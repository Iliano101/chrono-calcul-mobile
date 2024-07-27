package com.iliano.chrono_calcul_mobile.ui.screens.calculator

import android.util.Log
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
        setTargetTime(23, 59)
        viewModelScope.launch {
            while (true) {
                Log.d("MHM", "checkBoxState ${_uiState.value.checkBoxState}")

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
        setOffset(enable)
        updateResult()
    }


    private fun setTargetTime(hours: Int, minutes: Int) {
        _uiState.value.calculation.setTargetTime(hours, minutes)
    }

    private fun setOffset(enable: Boolean) {
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