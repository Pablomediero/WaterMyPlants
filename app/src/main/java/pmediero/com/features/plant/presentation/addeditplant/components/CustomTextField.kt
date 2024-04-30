package pmediero.com.features.plant.presentation.addeditplant.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import pmediero.com.R
import pmediero.com.core_ui.LocalSpacing

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isDescription: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    val colors = TextFieldDefaults.colors(
        disabledTextColor = Color.DarkGray,
        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        disabledLabelColor = Color.DarkGray,
        disabledPlaceholderColor = Color.DarkGray,
    )

    TextField(
        modifier = modifier,
        colors = colors,
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                placeholder,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            localFocusManager.clearFocus()
        }),
        maxLines = if (isDescription) Int.MAX_VALUE else 1,
    )
}

@Composable
fun CustomTextFieldModal(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    val colors = TextFieldDefaults.colors(
        disabledTextColor = Color.DarkGray,
        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        disabledLabelColor = Color.DarkGray,
        disabledPlaceholderColor = Color.DarkGray,
    )

    TextField(
        modifier = modifier
            .clickable { onClick() },
        colors = colors,
        value = value,
        onValueChange = {},
        readOnly = true,
        enabled = false,
        label = {
            Text(
                placeholder,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = null,
                modifier = modifier.padding(all = spacing.small)
            )
        },


        maxLines = 1,
    )
}

@Composable
fun CustomTextFieldPredicate(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    supportingText: @Composable () -> Unit,
    placeholder: String,
    onClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current

    val colors = TextFieldDefaults.colors(
        disabledTextColor = Color.DarkGray,
        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        disabledLabelColor = Color.DarkGray,
        disabledPlaceholderColor = Color.DarkGray,
    )

    TextField(
        modifier = modifier
            .clickable { onClick() },
        colors = colors,
        value = value,
        onValueChange = onValueChange,
        readOnly = false,
        enabled = true,
        label = {
            Text(
                placeholder,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingIcon = {
            Text(text = "ml", style = MaterialTheme.typography.bodyMedium.copy(Color.DarkGray))
        },
        supportingText = supportingText,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            localFocusManager.clearFocus()
        }),
        maxLines = 1,

        )
}

@Preview
@Composable
fun PreviewTextfield() {
    var description by rememberSaveable { mutableStateOf("") }
    val maxChar = 3
    CustomTextFieldPredicate(
        modifier = Modifier.fillMaxWidth(),
        value = "Textu",
        onValueChange = { },
        placeholder = stringResource(R.string.water_amount),
        supportingText = {
            Text(
                text = "${3} / $maxChar",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelSmall
            )
        },
        onClick = {}
    )
//    CustomTextField(
//        modifier = Modifier.fillMaxSize(),
//        value = description,
//        onValueChange = { description = it },
//        placeholder = "Description",
//        isDescription = true,
//    )
}
