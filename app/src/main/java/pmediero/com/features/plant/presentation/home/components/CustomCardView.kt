package pmediero.com.features.plant.presentation.home.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.plant.presentation._common.CustomIconButtonDefault

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CustomCardView(
    animatedVisibilityScope: AnimatedVisibilityScope,
    titleCard: String,
    subtitleCard: String,
    idElement: String,
    imageCard: String,
    labelCard: List<Any>,
    icon: Any,
    onClick: () -> Unit,
    onIconClicked: () -> Unit,
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
            animatedVisibilityScope = animatedVisibilityScope,
            idElement = idElement,
            imageCard = imageCard,
            labelCard = labelCard
        )
        BodyCardView(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .weight(1f)
                .fillMaxSize()
                .padding(spacing.small),
            spacing = spacing,
            animatedVisibilityScope = animatedVisibilityScope,
            titleCard = titleCard,
            subtitleCard = subtitleCard,
            icon = icon,
            onIconClicked = onIconClicked
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HeaderCardView(
    modifier: Modifier,
    spacing: Spacing,
    animatedVisibilityScope: AnimatedVisibilityScope,
    idElement : String,
    imageCard: String,
    labelCard: List<Any>
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
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "image/${idElement}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    )
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
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.small),
                verticalArrangement = Arrangement.spacedBy(spacing.small, Alignment.Top),
            ) {
                LazyColumn ( verticalArrangement = Arrangement.spacedBy(spacing.small)) {
                    items(labelCard) { itemTextLabel ->
                        Text(
                            text = itemTextLabel.toString(),
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
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.BodyCardView(
    modifier: Modifier,
    spacing: Spacing,
    animatedVisibilityScope: AnimatedVisibilityScope,
    titleCard: String,
    subtitleCard: String,
    icon: Any,
    onIconClicked: () -> Unit
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
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "text/${titleCard}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = {_, _ ->
                        tween(durationMillis = 1000)
                    }
                )
            )
            Text(
                text = subtitleCard,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "desc/${titleCard}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 1000)
                    }
                )
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = spacing.extraSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomIconButtonDefault(
                onClick = { onIconClicked() },
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
        Column {
//            CustomCardView(
//                titleCard = "Planta 1",
//                subtitleCard = "Descript",
//                labelCard = listOf("5 ml", "Mo, Tu, We, Th, Fr, Sa"),
//                icon = R.drawable.home_card_icon_water,
//                imageCard = "",
//                onClick = {},
//                onLongClick = {},
//                onIconClicked = {}
//            )
//            Spacer(modifier = Modifier.padding(12.dp))
//            CustomCardView(
//                titleCard = "Planta 1",
//                subtitleCard = "Descript",
//                labelCard = listOf("5 ml", "Mo","Tu","We","Th","Fr","Sa"),
//                icon = R.drawable.home_card_icon_water,
//                imageCard = "",
//                onClick = {},
//                onLongClick = {},
//                onIconClicked = {}
//            )
        }

    }
}