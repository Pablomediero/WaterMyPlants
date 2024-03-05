package pmediero.com.features.addplant.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import pmediero.com.core_ui.LocalSpacing

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isDescription: Boolean,
) {
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {}),
        maxLines = if (isDescription) Int.MAX_VALUE else 1,
    )
}

@Composable
fun  CustomTextFieldModal(
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {}),
        maxLines = 1,
    )
}

@Preview
@Composable
fun PreviewTextfield() {
    var description by rememberSaveable { mutableStateOf("") }

    CustomTextField(
        modifier = Modifier.fillMaxSize(),
        value = description,
        onValueChange = { description = it },
        placeholder = "Description",
        isDescription = true,
    )
}
