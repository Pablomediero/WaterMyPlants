package pmediero.com.features.plant.presentation.notification.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.R
import pmediero.com.core.presentation.common.CustomIconButton
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.WaterMyPlantsTheme

@Composable
fun Item(
    image: Painter,
    title: String,
    subtitle: String,
    buttonText: String,
    onButtonClick: () -> Unit,
) {
    val spacing = LocalSpacing.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(spacing.small)) {
        Column(
            modifier = Modifier
                .weight(3f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = image,
                modifier = Modifier
                    .size(90.dp)
                    .background(Color(0xFFDFF0DC))
                    .fillMaxWidth(),
                contentDescription = "Image",
                contentScale = ContentScale.Fit

            )
        }
        Column(
            modifier = Modifier
                .weight(7f),
            verticalArrangement = Arrangement.spacedBy(spacing.default, Alignment.CenterVertically),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = subtitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CustomIconButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(all = spacing.default),
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    style = MaterialTheme.typography.labelSmall,
                    icon = R.drawable.home_card_icon_water,
                    text = stringResource(R.string.mark_as_watered)
                )

            }
        }
    }
}


@Preview
@Composable
fun PreviewItem() {
    WaterMyPlantsTheme {
        Item(
            image = painterResource(id = R.drawable.add_plant_plant_icon_header),
            title = "Title ",
            subtitle = "Subtitle ",
            buttonText = "Button 1",
            onButtonClick = { /* Acción al hacer clic en el botón 1 */ },
        )
    }
}