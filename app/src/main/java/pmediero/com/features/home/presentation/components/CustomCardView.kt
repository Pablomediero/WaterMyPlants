package pmediero.com.features.home.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pmediero.com.R
import pmediero.com.core.presentation.common.CustomFloatingActionButton
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomCardView(
    titleCard: String,
    subtitleCard: String,
    imageCard: String,
    labelCard: List<String>,
    icon: Any,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Card(
        modifier = Modifier
            .combinedClickable(
                onClick = {
                    onClick()
                },
                onLongClick = {
                    onLongClick()
                }
            )
            .height(250.dp)
            .fillMaxWidth(),

        ) {
        HeaderCardView(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f),
            spacing = spacing,
            imageCard = imageCard,
            labelWateringAmount = labelCard[0],
            labelWateringDay = labelCard[1]
        )
        BodyCardView(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .weight(1f)
                .fillMaxSize()
                .padding(spacing.small),
            spacing = spacing,
            titleCard = titleCard,
            subtitleCard = subtitleCard,
            icon = icon
        )
    }
}

@Composable
fun HeaderCardView(
    modifier: Modifier,
    spacing: Spacing,
    imageCard: String,
    labelWateringAmount: String,
    labelWateringDay: String
) {
    Column(modifier = modifier) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDFF0DC)),
            contentAlignment = Alignment.Center
        ) {
            if (imageCard.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.add_plant_plant_icon_header),
                    modifier = Modifier
                        .padding(1.dp)
                        .width(61.dp)
                        .height(113.dp),
                    contentDescription = "image description",
                    contentScale = ContentScale.Crop

                )
            } else {

                AsyncImage(
                    model = imageCard,
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.small),
                verticalArrangement = Arrangement.spacedBy(spacing.small, Alignment.Top),
            ) {
                Text(
                    text = labelWateringAmount,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.56f),
                            shape = MaterialTheme.shapes.extraSmall
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
                Text(
                    text = labelWateringDay,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.56f),
                            shape = MaterialTheme.shapes.extraSmall
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)

                )

            }
        }

    }
}

@Composable
fun BodyCardView(
    modifier: Modifier,
    spacing: Spacing,
    titleCard: String,
    subtitleCard: String,
    icon: Any
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .weight(3f)
                .padding(start = spacing.small)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(
                spacing.extraSmall,
                Alignment.CenterVertically
            )
        ) {
            Text(
                text = titleCard,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = subtitleCard,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = spacing.extraSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomFloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                icon = icon,
                shape = MaterialTheme.shapes.small,
                isVisible = true,
                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewCustomCard() {
    WaterMyPlantsTheme {
        CustomCardView(
            titleCard = "Planta 1",
            subtitleCard = "Descript",
            labelCard = listOf("5 ml","Mo, Tu"),
            icon = R.drawable.home_card_icon_water,
            imageCard = "",
            onClick = {},
            onLongClick = {}
        )
    }
}