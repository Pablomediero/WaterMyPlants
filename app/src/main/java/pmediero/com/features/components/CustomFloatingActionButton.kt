package pmediero.com.features.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Icon(icon = icon)
    }
}

@Composable
fun CustomFloatingActionButtonWithText(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    icon: Any,
    text: String
){
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon = icon)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, style = MaterialTheme.typography.labelMedium)
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
fun PreviewCustomActionButton() {
    CustomFloatingActionButtonWithText(
        onClick = {},
        modifier = Modifier
            .padding(LocalSpacing.current.default)
            .width(151.dp)
            .height(48.dp)
            .padding(all = LocalSpacing.current.default),
        contentColor = MaterialTheme.colorScheme.onSecondary,
        containerColor = MaterialTheme.colorScheme.secondary,
        icon = R.drawable.add_plant_add_images_icon_header,
        text = "Add Image"
    )
}
