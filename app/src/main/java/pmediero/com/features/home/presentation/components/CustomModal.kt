package pmediero.com.features.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.R
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.WaterMyPlantsTheme

@Composable
fun DeletePlantConfirmationModal(
    showDialog: MutableState<Boolean>,
    itemName: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    val spacing = LocalSpacing.current
    if (showDialog.value) {
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.surface,
            onDismissRequest = { showDialog.value = false },
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.home_modal_icon_header),
                        contentDescription = "Some icon",
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(text = stringResource(R.string.are_you_sure), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(spacing.small))
                }
            },
            text = {
                Text(text = stringResource(
                    R.string.do_you_really_want_to_delete_the_this_process_cannot_be_undone,
                    itemName
                ))
            },
            confirmButton = {
                Button(
                    onClick = onConfirm,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    onClick = onCancel,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewModal() {
    WaterMyPlantsTheme {
        val showModal = remember { mutableStateOf(true) }
        DeletePlantConfirmationModal(showDialog = showModal,
           itemName = "Planta 1",
            onConfirm = { },
            onCancel = {  })
    }
}