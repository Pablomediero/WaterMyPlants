package pmediero.com.features.addplant.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.R
import pmediero.com.core.common.CustomFloatingActionButton
import pmediero.com.core.common.CustomFloatingActionButtonWithText
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.addplant.presentation.components.CustomTextField
import pmediero.com.features.addplant.presentation.components.CustomTextFieldModal
import pmediero.com.features.addplant.presentation.components.DialogPlantSize
import pmediero.com.features.addplant.presentation.components.DialogWateringDays
import pmediero.com.features.addplant.presentation.components.DialogWateringTime
import pmediero.com.features.addplant.presentation.components.PlantSize

@Composable
fun AddPlantScreenFigma() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            LocalSpacing.current.default,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderAddPlantFigma(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(all = LocalSpacing.current.default)
        )
        BodyAddPlantFigma(
            modifier = Modifier
                .weight(5f)
                .verticalScroll(rememberScrollState())
                .height(IntrinsicSize.Max)
                .shadow(
                    elevation = 3.dp,
                    spotColor = Color(0x4D000000),
                    ambientColor = Color(0x4D000000)
                )
                .shadow(
                    elevation = 8.dp,
                    spotColor = Color(0x26000000),
                    ambientColor = Color(0x26000000)
                )
                .padding(
                    top = LocalSpacing.current.medium,
                    start = LocalSpacing.current.medium,
                    end = LocalSpacing.current.medium,
                    bottom = LocalSpacing.current.default
                )
        )
        FooterAddPlantFigma(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = LocalSpacing.current.medium,
                    vertical = LocalSpacing.current.small
                )
        )
    }
}

@Composable
fun HeaderAddPlantFigma(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.add_plant_background_header),
                contentDescription = null,
                modifier = Modifier
                    .alpha(0.8f)
                    .width(484.dp)
                    .height(516.dp),
                contentScale = ContentScale.FillBounds

            )
            Image(
                painter = painterResource(id = R.drawable.add_plant_plant_icon_header),
                modifier = Modifier
                    .padding(1.dp)
                    .width(134.dp)
                    .height(242.dp),
                contentDescription = "image description",
                contentScale = ContentScale.None

            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(LocalSpacing.current.medium),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(452.dp)
                        .height(48.dp)

                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            LocalSpacing.current.default,
                            Alignment.Start
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CustomFloatingActionButton(
                            onClick = { },
                            modifier = Modifier
                                .width(48.dp)
                                .height(48.dp)
                                .clip(CircleShape),
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                            icon = Icons.Default.ArrowBack,
                            isVisible = true
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            LocalSpacing.current.small,
                            Alignment.End
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CustomFloatingActionButton(
                            onClick = { },
                            modifier = Modifier
                                .width(48.dp)
                                .height(48.dp)
                                .clip(CircleShape),
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            icon = Icons.TwoTone.Edit,
                            isVisible = false
                        )
                        CustomFloatingActionButton(
                            onClick = { },
                            modifier = Modifier
                                .width(48.dp)
                                .height(48.dp)
                                .clip(CircleShape),
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            icon = R.drawable.add_plant_cancel_icon_header,
                            isVisible = false
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        LocalSpacing.current.medium,
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CustomFloatingActionButtonWithText(
                        onClick = {},
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(all = LocalSpacing.current.default),
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        icon = R.drawable.add_plant_add_images_icon_header,
                        text = "Add Image"
                    )
                }
            }

        }
    }
}

@Composable
fun BodyAddPlantFigma(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.medium, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        FormAddPlantFigma(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = LocalSpacing.current.default)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAddPlantFigma(modifier: Modifier) {
    var plantName by rememberSaveable { mutableStateOf("") }
    var wateringDays by rememberSaveable { mutableStateOf("") }
    var wateringTime by rememberSaveable { mutableStateOf("") }
    var waterAmount by rememberSaveable { mutableStateOf("") }
    var plantSize by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    val showDialogPlantSize = remember { mutableStateOf(false) }
    val selectedSize = remember { mutableStateOf(PlantSize.Default) }

    val showDialogTimePicker = remember { mutableStateOf(false) }
    val timePickerStateHorizontal = rememberTimePickerState()

    val showDialogCheckBox = remember { mutableStateOf(false) }
    val checkboxState = remember { mutableStateMapOf<String, Boolean>().withDefault { false } }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.medium, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.small, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = plantName,
                onValueChange = { plantName = it },
                placeholder = "Plant name*",
                isDescription = false,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                LocalSpacing.current.medium,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextFieldModal(
                modifier = Modifier.weight(1f),
                value = wateringDays,
                onValueChange = { wateringDays = it },
                placeholder = "Watering days*",
                onClick = {
                    showDialogCheckBox.value = true
                },
            )
            CustomTextFieldModal(
                modifier = Modifier.weight(1f),
                value = wateringTime,
                onValueChange = { wateringTime = it },
                placeholder = "Watering time",
                onClick = {
                    showDialogTimePicker.value = true
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                LocalSpacing.current.medium,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextField(
                modifier = Modifier.weight(1f),
                value = waterAmount,
                onValueChange = { waterAmount = it },
                placeholder = "Water amount",
                isDescription = false,
            )
            CustomTextFieldModal(
                modifier = Modifier.weight(1f),
                value = plantSize,
                onValueChange = { plantSize = it },
                placeholder = "Plant size",
                onClick = {
                    showDialogPlantSize.value = true
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.small),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxSize(),
                value = description,
                onValueChange = { description = it },
                placeholder = "Description",
                isDescription = true,
            )
        }
    }
    DialogWateringDays(
        showDialog = showDialogCheckBox,
        checkboxState,
        onConfirm = { selectedDays ->
            wateringDays = selectedDays.filter { it.value }.keys.joinToString()
            showDialogCheckBox.value = false
        },
        onCancel = { showDialogCheckBox.value = false }
    )
    DialogWateringTime(
        showDialog = showDialogTimePicker,
        timePickerStateHorizontal,
        onConfirm = { selectedTime ->
            wateringTime = selectedTime
            showDialogTimePicker.value = false
        },
        onCancel = { showDialogPlantSize.value = false }
    )
    DialogPlantSize(
        showDialog = showDialogPlantSize,
        selectedSize = selectedSize,
        onConfirm = { selectedSizeOption ->
            plantSize = selectedSizeOption.toString()
            showDialogPlantSize.value = false
        },
        onCancel = { showDialogPlantSize.value = false }
    )
}


@Composable
fun FooterAddPlantFigma(modifier: Modifier) {
    CustomFloatingActionButtonWithText(
        modifier = modifier.fillMaxWidth(),
        onClick = { /*TODO*/ },
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = Color.White,
        icon = Icons.Outlined.Add,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        text = "Create plant"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAddPlantScreen() {
    WaterMyPlantsTheme {
        AddPlantScreenFigma()
    }
}