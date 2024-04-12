package pmediero.com.features.plant.presentation.detailplant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.R
import pmediero.com.core.presentation.common.CustomFloatingActionButton
import pmediero.com.core.presentation.common.CustomFloatingActionButtonWithText
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.plant.presentation.detailplant.components.CustomPoster

@Composable
fun DetailScreen() {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDFF0DC)),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.add_plant_plant_icon_header),
                modifier = Modifier.padding(top = spacing.extraLarge),
                contentDescription = "image description",
                contentScale = ContentScale.FillWidth
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(
                    spacing.default,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                HeaderDetailScreen(
                    modifier = Modifier
                        .weight(6f)
                        .fillMaxWidth()
                        .padding(all = spacing.default),
                    spacing = spacing,

                    )
                BodyDetailPlant(
                    modifier = Modifier
                        .weight(5f)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.extraLarge.copy(
                                bottomStart = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            )
                        )
                        .fillMaxWidth()
                        .padding(
                            top = spacing.medium,
                            start = spacing.medium,
                            end = spacing.medium,
                            bottom = spacing.default
                        ),
                    spacing = spacing
                )
                FooterDetailPlant(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(
                            horizontal = spacing.medium,
                            vertical = spacing.small
                        ),
                )
            }
        }
    }
}

@Composable
fun HeaderDetailScreen(
    modifier: Modifier,
    spacing: Spacing,
    //state: AddPlantState
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.medium),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(452.dp)
                    .height(48.dp)

            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        spacing.default,
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CustomFloatingActionButton(
                        onClick = { },
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                            .clip(CircleShape),
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        icon = Icons.Default.ArrowBack,
                        isVisible = true
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        spacing.small,
                        Alignment.End
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    CustomFloatingActionButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                            .clip(CircleShape),
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        icon = Icons.Outlined.Edit,
                        isVisible = true
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    spacing.medium,
                    Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val infoList = listOf(
                    "Watering Days" to "18:00",
                    "Watering Time" to "Mo Tu We Th Fr a",
                    "Water Amount" to "250 ml",
                )
                CustomPoster(
                    infoList = infoList,
                    containerColor = Color.White,
                    titleColor = Color.Black,
                    valueColor = Color.Black,
                    shape = MaterialTheme.shapes.small
                )
            }
        }
    }
}


@Composable
fun BodyDetailPlant(
    modifier: Modifier,
    spacing: Spacing,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.medium, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Nombre",
            style = MaterialTheme.typography.headlineMedium
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .height(IntrinsicSize.Max)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.example_large_text),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun FooterDetailPlant(modifier: Modifier) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomFloatingActionButtonWithText(
            onClick = {  },
            contentColor = MaterialTheme.colorScheme.surface,
            containerColor = MaterialTheme.colorScheme.primary,
            icon = Icons.Outlined.Add,
            text = stringResource(R.string.mark_as_watered)
        )
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    WaterMyPlantsTheme {
        DetailScreen()
    }
}