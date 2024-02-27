package pmediero.com.core.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.R
import pmediero.com.core_ui.LocalSpacing

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    icon: Any,
    isVisible: Boolean
) {
    if (isVisible) {
        FloatingActionButton(
            onClick = onClick,
            modifier = modifier,
            containerColor = containerColor,
            contentColor = contentColor
        ) {
            Icon(icon = icon)
        }
    }
}

@Composable
fun CustomFloatingActionButtonWithText(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    border: BorderStroke = BorderStroke(0.dp, Color.Transparent), // Set default to no border
    icon: Any,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        border = border,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        )
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon = icon)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Composable
fun Icon(icon: Any) {
    when (icon) {
        is Int -> {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null
            )
        }

        is ImageVector -> {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }

        else -> throw IllegalArgumentException("Unsupported icon type")
    }
}

@Preview
@Composable
fun PreviewIconButton() {
    CustomFloatingActionButtonWithText(
        onClick = {},
        modifier = Modifier.padding(all = LocalSpacing.current.default),
        contentColor = MaterialTheme.colorScheme.onSecondary,
        containerColor = MaterialTheme.colorScheme.secondary,
        icon = R.drawable.add_plant_add_images_icon_header,
        text = "Add Image"
    )
    CustomFloatingActionButtonWithText(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /*TODO*/ },
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = Color.White,
        icon = Icons.Outlined.Add,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        text = "Create plant"
    )
}
