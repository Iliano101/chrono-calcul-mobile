@file:OptIn(ExperimentalMaterial3Api::class)

package com.iliano.chrono_calcul_mobile.ui.screens.calculator

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iliano.chrono_calcul_mobile.R
import com.iliano.chrono_calcul_mobile.core.toFormatedString
import com.iliano.chrono_calcul_mobile.ui.components.TimePickerDialog
import java.time.LocalTime

@Composable
fun CalculatorScreen(calculatorViewModel: CalculatorViewModel = viewModel()) {
    val uiStateValue = calculatorViewModel.uiState.collectAsStateWithLifecycle().value

    val context = LocalContext.current

    LaunchedEffect(true) {
        calculatorViewModel.eventsFlow.collect { event ->
            when (event) {
                is CalculatorViewModel.ScreenEvent.EnteredTimeInvalid -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.invalid_time_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is CalculatorViewModel.ScreenEvent.GenericError -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.genericError),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ResultBox(uiStateValue.resultText)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
        ) {
            TimePickerDisplay(
                selectedTime = uiStateValue.calculation.getTargetTime(),
                onPress = { calculatorViewModel.showTimePicker() }
            )
            ToggleBox(
                checkedState = uiStateValue.checkBoxState,
                onCheckedChange = { calculatorViewModel.onOffsetToggle(it) })
        }
    }
    TimerPickerLogic(
        showTimePicker = uiStateValue.showTimePicker,
        timerPickerState = uiStateValue.timePickerState,
        closeTimePicker = { calculatorViewModel.hideTimePicker() }
    )
}

@Composable
fun TimerPickerLogic(
    showTimePicker: Boolean,
    timerPickerState: TimePickerState,
    closeTimePicker: () -> Unit
) {
    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { closeTimePicker() },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeTimePicker()
                    }
                ) { Text(stringResource(R.string.confirmButton)) }
            },
        )
        {
            TimePicker(state = timerPickerState)
        }
    }
}

@Composable
fun ResultBox(resultString: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)

    ) {
        Text(
            text = resultString,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun TimePickerDisplay(selectedTime: LocalTime, onPress: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable {
                onPress()
            }
    ) {
        Text(
            text = selectedTime.toFormatedString(),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)

        )
    }
}

@Composable
fun ToggleBox(checkedState: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth(0.5f)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.offset_switch),
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Switch(checked = checkedState, onCheckedChange = onCheckedChange)
        }
    }
}

