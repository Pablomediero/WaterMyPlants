package pmediero.com.features.home.presentation.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.R
import pmediero.com.core.model.local.Plant
import pmediero.com.core.presentation.common.CustomFloatingActionButton
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomCardView(
    plant: Plant,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    OutlinedCard(
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
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        HeaderCardView(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f),
            spacing = spacing,
            labelWateringAmount = plant.waterAmount,
            labelWateringDay = plant.wateringDays
        )
        BodyCardView(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .weight(1f)
                .fillMaxSize()
                .padding(spacing.small),
            spacing = spacing,
            titleCard = plant.name,
            subtitleCard = plant.description
        )
    }
}

@Composable
fun HeaderCardView(
    modifier: Modifier,
    spacing: Spacing,
    labelWateringAmount: String,
    labelWateringDay: String
) {
    Column(modifier = modifier) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.add_plant_plant_icon_header),
                modifier = Modifier
                    .padding(1.dp)
                    .width(61.dp)
                    .height(113.dp),
                contentDescription = "image description",
                contentScale = ContentScale.Crop

            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.small),
                verticalArrangement = Arrangement.spacedBy(spacing.extraSmall, Alignment.Top),
            ) {
                Text(
                    text = labelWateringAmount,
                    style = TextStyle(
                        color = Color.White,

                        ),
                    modifier = Modifier
                        .alpha(0.8f)
                        .background(
                            color = MaterialTheme.colorScheme.outline,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(spacing.extraSmall)
                )
                Text(
                    text = labelWateringDay,
                    style = TextStyle(
                        color = Color.White,

                        ),
                    modifier = Modifier
                        .alpha(0.8f)
                        .background(
                            color = MaterialTheme.colorScheme.outline,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(spacing.extraSmall)

                )

            }
        }

    }
}

@Composable
fun BodyCardView(modifier: Modifier, spacing: Spacing, titleCard: String, subtitleCard: String) {
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
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = subtitleCard,
                style = MaterialTheme.typography.titleSmall
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
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = Color.White,
                icon = Icons.Filled.Settings,
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
            Plant(
                id = 0,
                name = "Planta 1",
                plantSize = "Small",
                waterAmount = "5 ml",
                wateringTime = "12:00",
                wateringDays = "EveryDay",
                description = "Descript"
            ),
            onClick = {},
            onLongClick =  {}
        )
    }
}