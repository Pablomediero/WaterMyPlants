package pmediero.com.features.addplant.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pmediero.com.R
import pmediero.com.core.model.local.Plant
import pmediero.com.core.presentation.common.CustomFloatingActionButton
import pmediero.com.core.presentation.common.CustomFloatingActionButtonWithText
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.addplant.presentation.components.CustomTextField
import pmediero.com.features.addplant.presentation.components.CustomTextFieldModal
import pmediero.com.features.addplant.presentation.components.DialogPlantSize
import pmediero.com.features.addplant.presentation.components.DialogWateringDays
import pmediero.com.features.addplant.presentation.components.DialogWateringTime
import pmediero.com.features.addplant.presentation.components.PlantSize
import pmediero.com.features.addplant.presentation.root.AddPlantAction
import pmediero.com.features.addplant.presentation.root.AddPlantState

@Composable
fun AddPlantScreen(
    state: AddPlantState,
    onAction: (AddPlantAction) -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            spacing.default,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderAddPlant(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(all = spacing.default),
            spacing = spacing,
            state = state,
            onAddImageButtonClick = { imageUrl ->
                onAction(AddPlantAction.OnAddImageButtonClick(imageUrl))
            },
            onRemoveImageButtonClick = {
                onAction(AddPlantAction.OnRemoveImageButtonClick)
            }

        )
        BodyAddPlant(
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
                    top = spacing.medium,
                    start = spacing.medium,
                    end = spacing.medium,
                    bottom = spacing.default
                ),
            state = state,
            spacing = spacing,
            onPlantNameChange = { plantName ->
                onAction(AddPlantAction.OnPlantNameChange(plantName))
            },
            onPlantWateringDaysChange = { wateringDays ->
                onAction(AddPlantAction.OnPlantWateringDaysChange(wateringDays))
            },
            onPlantWateringTimeChange = { wateringTime ->
                onAction(AddPlantAction.OnPlantWateringTimeChange(wateringTime))
            },
            onPlantWaterAmountChange = { waterAmount ->
                onAction(AddPlantAction.OnPlantWaterAmountChange(waterAmount))
            },
            onPlantSizeChange = { plantSize ->
                onAction(AddPlantAction.OnPlantSizeChange(plantSize))
            },
            onPlantDescriptionChange = { plantDescription ->
                onAction(AddPlantAction.OnPlantDescriptionChange(plantDescription))
            }

        )
        FooterAddPlant(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacing.medium,
                    vertical = spacing.small
                ),
            onFooterBtnClick = {
                onAction(
                    AddPlantAction.OnCreatePlantClick(
                        Plant(
                            name = state.plantName,
                            wateringDays = state.wateringDays,
                            wateringTime = state.wateringTime,
                            waterAmount = state.waterAmount,
                            plantSize = state.plantSize,
                            description = state.plantDescription,
                            photo = state.plantPhoto
                        )
                    )
                )
            },
        )
    }
}

@Composable
fun HeaderAddPlant(
    modifier: Modifier,
    spacing: Spacing,
    state: AddPlantState,
    onAddImageButtonClick: (String) -> Unit,
    onRemoveImageButtonClick: () -> Unit

) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            uri?.toString()?.let { imageUrl ->
                onAddImageButtonClick(imageUrl)
            }
        }
    )

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
            if (state.plantPhoto.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.add_plant_background_header),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(0.8f)
                        .fillMaxSize(),
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
            } else {
                AsyncImage(
                    model = state.plantPhoto,
                    contentDescription = "image description",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.medium),
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
                            spacing.default,
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
                            spacing.small,
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
                            onClick = {
                              onRemoveImageButtonClick()
                            },
                            modifier = Modifier
                                .width(48.dp)
                                .height(48.dp)
                                .clip(CircleShape),
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            icon = R.drawable.add_plant_cancel_icon_header,
                            isVisible = state.isPhotoSelected
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        spacing.medium,
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CustomFloatingActionButtonWithText(
                        onClick = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(all = spacing.default),
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        icon =if(state.isPhotoSelected) {R.drawable.add_plant_change_image_icon_header} else {R.drawable.add_plant_add_images_icon_header},
                        text = stringResource(if(state.isPhotoSelected){R.string.change_image} else {R.string.add_image})
                    )
                }
            }
        }
    }
}

@Composable
fun BodyAddPlant(
    modifier: Modifier,
    state: AddPlantState,
    spacing: Spacing,
    onPlantNameChange: (String) -> Unit,
    onPlantWateringDaysChange: (Map<String, Boolean>) -> Unit,
    onPlantWateringTimeChange: (String) -> Unit,
    onPlantWaterAmountChange: (String) -> Unit,
    onPlantSizeChange: (String) -> Unit,
    onPlantDescriptionChange: (String) -> Unit

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.medium, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        FormAddPlantFigma(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = spacing.default),
            spacing = spacing,
            state = state,
            onPlantNameChange = onPlantNameChange,
            onPlantWateringDaysChange = onPlantWateringDaysChange,
            onPlantWateringTimeChange = onPlantWateringTimeChange,
            onPlantWaterAmountChange = onPlantWaterAmountChange,
            onPlantSizeChange = onPlantSizeChange,
            onPlantDescriptionChange = onPlantDescriptionChange
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAddPlantFigma(
    modifier: Modifier,
    spacing: Spacing,
    state: AddPlantState,
    onPlantNameChange: (String) -> Unit,
    onPlantWateringDaysChange: (Map<String, Boolean>) -> Unit,
    onPlantWateringTimeChange: (String) -> Unit,
    onPlantWaterAmountChange: (String) -> Unit,
    onPlantSizeChange: (String) -> Unit,
    onPlantDescriptionChange: (String) -> Unit
) {

    val showDialogPlantSize = remember { mutableStateOf(false) }
    val selectedSize = remember { mutableStateOf(PlantSize.Default) }

    val showDialogTimePicker = remember { mutableStateOf(false) }
    val timePickerStateHorizontal = rememberTimePickerState(is24Hour = true)

    val showDialogCheckBox = remember { mutableStateOf(false) }
    val checkboxState = remember { mutableStateMapOf<String, Boolean>().withDefault { false } }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.medium, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing.small, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.plantName,
                onValueChange = { onPlantNameChange(it) },
                placeholder = stringResource(R.string.plant_name),
                isDescription = false,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                spacing.medium,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextFieldModal(
                modifier = Modifier.weight(1f),
                value = state.wateringDays,
                placeholder = stringResource(R.string.watering_days),
                onClick = {
                    showDialogCheckBox.value = true
                },
            )
            CustomTextFieldModal(
                modifier = Modifier.weight(1f),
                value = state.wateringTime,
                placeholder = stringResource(R.string.watering_time),
                onClick = {
                    showDialogTimePicker.value = true
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                spacing.medium,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextField(
                modifier = Modifier.weight(1f),
                value = state.waterAmount,
                onValueChange = { onPlantWaterAmountChange(it) },
                placeholder = stringResource(R.string.water_amount),
                isDescription = false,
            )
            CustomTextFieldModal(
                modifier = Modifier.weight(1f),
                value = state.plantSize,
                placeholder = stringResource(R.string.plant_size),
                onClick = {
                    showDialogPlantSize.value = true
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxSize(),
                value = state.plantDescription,
                onValueChange = { onPlantDescriptionChange(it) },
                placeholder = stringResource(R.string.description),
                isDescription = true,
            )
        }
    }
    DialogWateringDays(
        showDialog = showDialogCheckBox,
        checkboxState,
        onConfirm = { selectedDays ->
            onPlantWateringDaysChange(selectedDays)
            showDialogCheckBox.value = false
        },
        onCancel = { showDialogCheckBox.value = false }
    )
    DialogWateringTime(
        showDialog = showDialogTimePicker,
        timePickerStateHorizontal,
        onConfirm = { selectedTime ->
            onPlantWateringTimeChange(selectedTime)
            showDialogTimePicker.value = false
        }
    ) { showDialogPlantSize.value = false }
    DialogPlantSize(
        showDialog = showDialogPlantSize,
        selectedSize = selectedSize,
        onConfirm = { selectedSizeOption ->
            onPlantSizeChange(selectedSizeOption.toString())
            showDialogPlantSize.value = false
        },
        onCancel = { showDialogPlantSize.value = false }
    )
}


@Composable
fun FooterAddPlant(modifier: Modifier, onFooterBtnClick: () -> Unit) {
    CustomFloatingActionButtonWithText(
        modifier = modifier.fillMaxWidth(),
        onClick = onFooterBtnClick,
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = Color.White,
        icon = Icons.Outlined.Add,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        text = stringResource(R.string.create_plant)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAddPlantScreen() {
    WaterMyPlantsTheme {
        AddPlantScreen(AddPlantState()) {}
    }
}