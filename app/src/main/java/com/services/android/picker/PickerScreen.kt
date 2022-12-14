package com.services.android.picker

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.services.android.core.Event
import com.services.android.picker.events.ForegroundServiceButtonPressed
import org.koin.androidx.compose.viewModel

@Composable
fun PickerScreen() {
    val viewModel: PickerViewModel by viewModel()
}


@Composable
private fun ForegroundServiceButton(
    modifier: Modifier,
    eventHandler: (Event) -> Unit,
) {
    Button(
        onClick = { eventHandler(ForegroundServiceButtonPressed) },

    ) {

    }
}