package pmediero.com.core.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    shape: Shape = FloatingActionButtonDefaults.shape,
    modifier: Modifier = Modifier,
    icon: Any,
    isVisible: Boolean
) {
    if (isVisible) {
        FloatingActionButton(
            onClick = onClick,
            modifier = modifier,
            elevation = elevation,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor
        ) {
            Icon(icon = icon)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFloatingActionButtonNotification(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    icon: Any,
    isVisible: Boolean,
    isNotify: Boolean = false
) {
    if (isVisible) {
        Box(
            modifier = modifier,
        ) {
            FloatingActionButton(
                onClick = onClick,
                shape = MaterialTheme.shapes.extraLarge,
                containerColor = containerColor,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(icon = icon)
            }
            if (isNotify) {
                Badge(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = contentColor
                ) {

                }
            }
        }
    }
}


@Composable
fun CustomFloatingActionButtonWithText(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    border: BorderStroke = BorderStroke(0.dp, Color.Transparent),
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

}
