package pmediero.com.features.addplant.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isModal: Boolean,
    isDescription: Boolean,
    onClick: () -> Unit
) {
    val colors = TextFieldDefaults.textFieldColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        disabledTextColor = Color.DarkGray,
        disabledLabelColor = Color.DarkGray,
        disabledPlaceholderColor = Color.DarkGray
    )

    TextField(
        modifier = modifier.clickable { onClick() },
        colors = colors,
        value = value,
        onValueChange = onValueChange,
        readOnly = isModal,
        enabled = !isModal,
        label = {
            Text(placeholder, style = MaterialTheme.typography.bodyLarge)
        },
        trailingIcon = if (isModal) {
            {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        } else {
            null
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {

        }),
        maxLines = if (isDescription) Int.MAX_VALUE else 1,


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
        isModal = false,
        isDescription = true,
        onClick = {}
    )
}
