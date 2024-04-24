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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pmediero.com.R
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.WaterMyPlantsTheme

@Composable
fun Item(
    image: String,
    title: String,
    subtitle: String,
    linkText: String,
    onLinkTextClick: () -> Unit,
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(spacing.small),
    ) {
        Column(
            modifier = Modifier
                .weight(3f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(90.dp)
                    .background(Color(0xFFDFF0DC), shape = MaterialTheme.shapes.medium)
            )
            {
                if (image.isEmpty()) {

                    Image(
                        painter = painterResource(id = R.drawable.add_plant_plant_icon_header),
                        modifier = Modifier.size(70.dp),
                        contentDescription = "Image",
                        contentScale = ContentScale.Fit

                    )

                } else {
                    AsyncImage(
                        model = image,
                        modifier = Modifier
                            .fillMaxSize().background(Color(0xFFDFF0DC), shape = MaterialTheme.shapes.medium),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(7f),
            verticalArrangement = Arrangement.spacedBy(
                spacing.extraSmall,
                Alignment.CenterVertically
            ),
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth().weight(2f),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = subtitle,
                modifier = Modifier
                    .fillMaxWidth().weight(2f),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall.copy(MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Text(
                text = linkText,
                modifier = Modifier
                    .fillMaxSize().weight(1f)
                    .clickable {
                               onLinkTextClick()
                    },
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall.copy(MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}


@Preview
@Composable
fun PreviewItem() {
    WaterMyPlantsTheme {
        Item(
            image = "",
            title = "Title ",
            subtitle = "Subtitle ",
            linkText = "Button 1",
            onLinkTextClick = { /* Acción al hacer clic en el botón 1 */ },
        )
    }
}