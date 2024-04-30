package pmediero.com.features.plant.presentation.detailplant

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pmediero.com.R
import pmediero.com.core.model.local.Plant
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.plant.presentation._common.CustomIconButton
import pmediero.com.features.plant.presentation._common.CustomIconButtonDefault
import pmediero.com.features.plant.presentation.detailplant.components.CustomPoster
import pmediero.com.features.plant.presentation.detailplant.root.DetailAction
import pmediero.com.features.plant.presentation.detailplant.root.DetailState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    state: DetailState,
    onAction: (DetailAction) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val spacing = LocalSpacing.current
    val height = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDFF0DC)),
        contentAlignment = Alignment.TopCenter
    ) {
        if (state.plant.photo.isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.add_plant_plant_icon_header),
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${state.plant.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        placeHolderSize = SharedTransitionScope.PlaceHolderSize.animatedSize,
                        boundsTransform = {_, _ ->
                            tween(durationMillis = 1000)
                        }
                    )
                    .padding(top = spacing.large),
                contentDescription = "image description",
                contentScale = ContentScale.FillWidth
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(Color(0xFFDFF0DC)),
                contentAlignment = Alignment.TopCenter
            ) {
                AsyncImage(
                    model = state.plant.photo,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .height(height * 0.9f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(
                    spacing.default,
                    Alignment.Top
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                HeaderDetailPlant(
                    modifier = Modifier
                        .height(height * 0.45f)
                        .fillMaxWidth()
                        .padding(all = spacing.medium),
                    spacing = spacing,
                    state = state,
                    onEditButtonClick = { plantIdParam ->
                        onAction(DetailAction.OnEditButtonClick(plantIdParam))
                    },
                    onReturnClick = {
                        onAction(DetailAction.OnReturnClick)
                    }
                )
                BodyDetailPlant(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.extraLarge.copy(
                                bottomStart = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            )
                        )
                        .defaultMinSize(minHeight = height * 0.45f)
                        .fillMaxWidth()
                        .padding(
                            top = spacing.medium,
                            start = spacing.medium,
                            end = spacing.medium,
                            bottom = spacing.default
                        ),
                    spacing = spacing,
                    animatedVisibilityScope = animatedVisibilityScope,
                    state = state,
                )

            }
            Column(
                modifier = Modifier
                    .height(height * 0.1f)
                    .background(MaterialTheme.colorScheme.surface),
                verticalArrangement = Arrangement.spacedBy(
                    spacing.default,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                FooterDetailPlant(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(
                            horizontal = spacing.medium,
                            vertical = spacing.small
                        ),
                    state = state,
                    onIsWaterUpdateButtonClick = { plant ->
                        onAction(DetailAction.OnIsWaterUpdateButtonClick(plant))
                    }

                )
            }
        }
    }
}

@Composable
fun HeaderDetailPlant(
    modifier: Modifier,
    spacing: Spacing,
    state: DetailState,
    onEditButtonClick: (String) -> Unit,
    onReturnClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()

        ) {
            CustomIconButtonDefault(
                onClick = {
                    onReturnClick()
                },
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clip(CircleShape),
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                icon = Icons.Default.ArrowBack,
                isVisible = true
            )
            CustomIconButtonDefault(
                onClick = {
                    onEditButtonClick(state.plant.id)
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                spacing.medium,
                Alignment.Start
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val infoList = mutableListOf(
                stringResource(R.string.watering_days) to state.plant.wateringDays,
                stringResource(R.string.watering_time) to state.plant.wateringTime,

                )
            if (state.plant.waterAmount.isNotEmpty()) {
                infoList.add(stringResource(R.string.water_amount) to state.plant.waterAmount + " ml")
            }
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


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.BodyDetailPlant(
    modifier: Modifier,
    spacing: Spacing,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: DetailState,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.medium, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.sharedBounds(
                sharedContentState = rememberSharedContentState(key = "text/${state.plant.name}"),
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = { _, _ ->
                    tween(durationMillis = 1000)
                }
            ).fillMaxWidth(),
            text = state.plant.name,
            style = MaterialTheme.typography.headlineMedium
        )
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Text(
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "desc/${state.plant.name}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 1000)
                    }
                ).fillMaxWidth(),
                text = state.plant.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun FooterDetailPlant(
    modifier: Modifier,
    state: DetailState,
    onIsWaterUpdateButtonClick: (Plant) -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomIconButton(
            onClick = { onIsWaterUpdateButtonClick(state.plant) },
            contentColor = MaterialTheme.colorScheme.surface,
            containerColor = MaterialTheme.colorScheme.primary,
            icon = if (!state.plant.isWatered) R.drawable.home_card_icon_water else Icons.Filled.Check,
            text = stringResource(if (!state.plant.isWatered) R.string.mark_as_watered else R.string.mark_as_unwatered)
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun DetailScreenPreview() {
    WaterMyPlantsTheme {
//        SharedTransitionScope {
//            DetailScreen(state = DetailState(), onAction = {})
//        }
    }
}