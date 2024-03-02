package pmediero.com.features.addplant.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.core_ui.WaterMyPlantsTheme


@Composable
fun DialogWateringDays(
    showDialog: MutableState<Boolean>,
    checkboxStates: MutableMap<String, Boolean>,
    onConfirm: (Map<String, Boolean>) -> Unit,
    onCancel: () -> Unit
) {
    val daysOfWeek = listOf(
        "Everyday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            {
                Text(
                    text = "Watering days",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    daysOfWeek.forEach { item ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    toggleCheckboxState(
                                        item = item,
                                        daysOfWeek = daysOfWeek,
                                        checkboxStates = checkboxStates
                                    )
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = checkboxStates[item] ?: false,
                                onCheckedChange = {
                                    toggleCheckboxState(
                                        item = item,
                                        daysOfWeek = daysOfWeek,
                                        checkboxStates = checkboxStates
                                    )
                                },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = item, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onCancel) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = { onConfirm(checkboxStates) }) {
                        Text(text = "Confirm")
                    }
                }
            }
        )
    }
}


fun toggleCheckboxState(item: String, daysOfWeek: List<String>, checkboxStates: MutableMap<String, Boolean>) {
    if (item == "Everyday") {
        val newValue = !(checkboxStates[item] ?: false)
        daysOfWeek.forEach { day ->
            checkboxStates[day] = newValue
        }
    } else {
        checkboxStates["Everyday"] = false
        checkboxStates[item] = !(checkboxStates[item] ?: false)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogWateringTime(
    showDialog: MutableState<Boolean>,
    timePickerState: TimePickerState,
    onConfirm: (String) -> Unit,
    onCancel: () -> Unit
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            {
                Text(
                    text = "Watering hour ",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    TimeInput(state = timePickerState)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onCancel) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = {
                        onConfirm(
                            String.format(
                                "%02d:%02d",
                                timePickerState.hour,
                                timePickerState.minute
                            )
                        )
                    }) {
                        Text(text = "Confirm")
                    }
                }
            }
        )
    }
}

@Composable
fun DialogPlantSize(
    showDialog: MutableState<Boolean>,
    selectedSize: MutableState<PlantSize>,
    onConfirm: (PlantSize) -> Unit,
    onCancel: () -> Unit
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            {
                Text(
                    text = "Plant size",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    PlantSizeRadioButton(
                        text = "Small",
                        size = PlantSize.Small,
                        selectedSize = selectedSize
                    )
                    PlantSizeRadioButton(
                        text = "Medium",
                        size = PlantSize.Medium,
                        selectedSize = selectedSize
                    )
                    PlantSizeRadioButton(
                        text = "Large",
                        size = PlantSize.Large,
                        selectedSize = selectedSize
                    )
                    PlantSizeRadioButton(
                        text = "Extra Large",
                        size = PlantSize.ExtraLarge,
                        selectedSize = selectedSize
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onCancel) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = { onConfirm(selectedSize.value) }) {
                        Text(text = "Confirm")
                    }
                }
            },
        )
    }
}

@Composable
private fun PlantSizeRadioButton(
    text: String,
    size: PlantSize,
    selectedSize: MutableState<PlantSize>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { selectedSize.value = size },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedSize.value == size,
            onClick = { selectedSize.value = size }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = MaterialTheme.colorScheme.onSurface)
    }
}

enum class PlantSize {
    Default,
    Small,
    Medium,
    Large,
    ExtraLarge
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewDialog() {
    WaterMyPlantsTheme {
        val showDialogPlantSize = remember { mutableStateOf(false) }
        val selectedSize = remember { mutableStateOf(PlantSize.Default) }
        DialogPlantSize(
            showDialog = showDialogPlantSize,
            selectedSize = selectedSize,
            onConfirm = {
            },
            onCancel = { }
        )

        val showDialogTimeInput = remember { mutableStateOf(false) }
        val timePickerStateHorizontal = rememberTimePickerState()
        DialogWateringTime(
            showDialog = showDialogTimeInput,
            timePickerState = timePickerStateHorizontal,
            onConfirm = {},
            onCancel = {}
        )

        val showDialogCheckBox = remember { mutableStateOf(true) }
        val checkboxState = remember { mutableStateMapOf<String, Boolean>().withDefault { false } }
        DialogWateringDays(
            showDialog = showDialogCheckBox,
            checkboxState,
            onConfirm = {
            },
            onCancel = { showDialogCheckBox.value = false }
        )

    }
}
