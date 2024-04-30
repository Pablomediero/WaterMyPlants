package pmediero.com.features.plant.presentation.addeditplant

import android.content.Intent
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
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pmediero.com.R
import pmediero.com.core.model.local.Plant
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.plant.presentation._common.CustomIconButtonDefault
import pmediero.com.features.plant.presentation._common.CustomIconButton
import pmediero.com.features.plant.presentation._common.DialogPlantSize
import pmediero.com.features.plant.presentation._common.DialogWateringDays
import pmediero.com.features.plant.presentation._common.DialogWateringTime
import pmediero.com.features.plant.presentation._common.PlantSize
import pmediero.com.features.plant.presentation.addeditplant.components.CustomTextField
import pmediero.com.features.plant.presentation.addeditplant.components.CustomTextFieldModal
import pmediero.com.features.plant.presentation.addeditplant.components.CustomTextFieldPredicate
import pmediero.com.features.plant.presentation.addeditplant.root.AddEditPlantAction
import pmediero.com.features.plant.presentation.addeditplant.root.AddEditPlantState

@Composable
fun AddEditPlantScreen(
    state: AddEditPlantState,
    onAction: (AddEditPlantAction) -> Unit
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
        HeaderAddEditPlant(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(all = spacing.default),
            spacing = spacing,

            state = state,
            onReturnButtonClick = {
                onAction(AddEditPlantAction.OnReturnButtonClick)
            },
            onAddImageButtonClick = { imageUrl ->
                onAction(AddEditPlantAction.OnAddImageButtonClickEdit(imageUrl))
            },
            onRemoveImageButtonClick = {
                onAction(AddEditPlantAction.OnRemoveImageButtonClick)
            }

        )
        BodyAddEditPlant(
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
                onAction(AddEditPlantAction.OnEditPlantNameChange(plantName))
            },
            onPlantWateringDaysChange = { wateringDays ->
                onAction(AddEditPlantAction.OnEditPlantWateringDaysChange(wateringDays))
            },
            onPlantWateringTimeChange = { wateringTime ->
                onAction(AddEditPlantAction.OnEditPlantWateringTimeChange(wateringTime))
            },
            onPlantWaterAmountChange = { waterAmount ->
                onAction(AddEditPlantAction.OnEditPlantWaterAmountChange(waterAmount))
            },
            onPlantSizeChange = { plantSize ->
                onAction(AddEditPlantAction.OnEditPlantSizeChange(plantSize))
            },
            onPlantDescriptionChange = { plantDescription ->
                onAction(AddEditPlantAction.OnEditPlantDescriptionChange(plantDescription))
            }

        )
        FooterAddEditPlant(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spacing.medium,
                    vertical = spacing.small
                ),
            state = state,
            onFooterBtnClick = {
                onAction(
                    AddEditPlantAction.OnCreateEditPlantClick(
                        Plant(
                            id = state.plantId,
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
fun HeaderAddEditPlant(
    modifier: Modifier,
    spacing: Spacing,
    state: AddEditPlantState,
    onReturnButtonClick: () -> Unit,
    onAddImageButtonClick: (String) -> Unit,
    onRemoveImageButtonClick: () -> Unit

) {
    val context = LocalContext.current
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            uri?.toString()?.let { imageUrl ->
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(uri, flag)
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
            if (state.plantPhoto == "") {
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
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            spacing.default,
                            Alignment.Start
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CustomIconButtonDefault(
                            onClick = {
                                onReturnButtonClick()
                            },
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
                        CustomIconButtonDefault(
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
                        CustomIconButtonDefault(
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
                    CustomIconButton(
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
                        icon = if (state.isPhotoSelected) {
                            R.drawable.add_plant_change_image_icon_header
                        } else {
                            R.drawable.add_plant_add_images_icon_header
                        },
                        text = stringResource(
                            if (state.isPhotoSelected) {
                                R.string.change_image
                            } else {
                                R.string.add_image
                            }
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun BodyAddEditPlant(
    modifier: Modifier,
    state: AddEditPlantState,
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
    state: AddEditPlantState,
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
    //val timePickerStateHorizontal = rememberTimePickerState(is24Hour = true)

    val showDialogCheckBox = remember { mutableStateOf(false) }
    val checkboxState = remember { mutableStateMapOf<String, Boolean>().withDefault { false } }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.default, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = spacing.extraSmall),
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
            modifier = Modifier.padding(top = spacing.medium,bottom = spacing.extraSmall),
            horizontalArrangement = Arrangement.spacedBy(
                spacing.medium,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextFieldModal(
                modifier = Modifier.weight(1f),
                value = state.wateringDays,
                placeholder = stringResource(R.string.label_watering_days),
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
            modifier = Modifier.padding(top = spacing.medium, bottom = spacing.extraSmall),
            horizontalArrangement = Arrangement.spacedBy(
                spacing.medium,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.Top
        ) {
            val maxChar = 3
            val pattern = remember { Regex("^\\d*\$") }
            CustomTextFieldPredicate(
                modifier = Modifier.weight(1f),
                value = state.waterAmount,
                onValueChange = {
                    if (it.length <= maxChar && (it.matches(pattern) || it.isEmpty())) onPlantWaterAmountChange(
                        it
                    )
                },
                placeholder = stringResource(R.string.water_amount),
                supportingText = {
                    Text(
                        text = "${state.waterAmount.length} / $maxChar",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.labelSmall.copy(Color.DarkGray)
                    )
                },
                onClick = {}
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
        //SPACING HERE?
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
fun FooterAddEditPlant(modifier: Modifier, state: AddEditPlantState, onFooterBtnClick: () -> Unit) {
    CustomIconButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onFooterBtnClick()
        },
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = Color.White,
        icon = if (state.isEditPlant) Icons.Outlined.Edit else Icons.Outlined.Add,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        text = stringResource(if (state.isEditPlant) R.string.edit_plant else R.string.create_plant)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAddPlantScreen() {
    WaterMyPlantsTheme {
        AddEditPlantScreen(AddEditPlantState()) {}
    }
}