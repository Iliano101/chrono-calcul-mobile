package com.iliano.chrono_calcul_mobile.ui.screens.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime

@Composable
fun CalculatorScreen(calculatorViewModel: CalculatorViewModel = CalculatorViewModel()) {
    val uiStateValue = calculatorViewModel.uiState.collectAsStateWithLifecycle().value

    val timeDialogState = rememberMaterialDialogState()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .height(256.dp)
                .width(512.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)

        ) {
            Text(text = uiStateValue.displayText)
        }

        Box(
            modifier = Modifier
                .height(256.dp)
                .width(512.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)

        ) {
            Button(onClick = {
                timeDialogState.show()
            }) {
                Text(text = "Test picker")
            }
        }
    }

    MaterialDialog(
        dialogState = timeDialogState,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
        backgroundColor = MaterialTheme.colorScheme.background,
        buttons = { positiveButton(text = "Ok") },
    ) {
        this.timepicker(
            initialTime = LocalTime.now(),
            title = "title test",
            is24HourClock = true
            //  timeRange = LocalTime.now()..LocalTime.MIDNIGHT
        ) {
            calculatorViewModel.updateTargetTimeWithLocalTime(it)
        }
    }
}

