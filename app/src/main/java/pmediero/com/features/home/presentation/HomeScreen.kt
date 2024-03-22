package pmediero.com.features.home.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.R
import pmediero.com.core.model.local.Plant
import pmediero.com.core.presentation.common.CustomFloatingActionButtonNotification
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.home.presentation.components.CustomCardView
import pmediero.com.features.home.presentation.components.DeletePlantConfirmationModal
import pmediero.com.features.home.presentation.root.HomeAction
import pmediero.com.features.home.presentation.root.HomeState

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    val listaPlantas = listOf(
        Plant(
            id = 0,
            name = "Planta 1",
            plantSize = "Small",
            waterAmount = "5 ml",
            wateringTime = "12:00",
            wateringDays = "EveryDay",
            description = "Descript"
        ),
        Plant(
            id = 1,
            name = "Planta 2",
            plantSize = "Large",
            waterAmount = "25 ml",
            wateringTime = "2:00",
            wateringDays = "Fr Sa",
            description = "Descript"
        ),
        Plant(
            id = 2,
            name = "Planta 3",
            plantSize = "Medium",
            waterAmount = "80 ml",
            wateringTime = "17:00",
            wateringDays = "Mo",
            description = "Descript"
        ),
        Plant(
            id = 3,
            name = "Planta 4",
            plantSize = "Extra Large",
            waterAmount = "50 ml",
            wateringTime = "19:00",
            wateringDays = "We",
            description = "Descript"
        ),
        Plant(
            id = 0,
            name = "Planta 1",
            plantSize = "Small",
            waterAmount = "5 ml",
            wateringTime = "12:00",
            wateringDays = "EveryDay",
            description = "Descript"
        ),
        Plant(
            id = 1,
            name = "Planta 2",
            plantSize = "Large",
            waterAmount = "25 ml",
            wateringTime = "2:00",
            wateringDays = "Fr Sa",
            description = "Descript"
        ),
        Plant(
            id = 2,
            name = "Planta 3",
            plantSize = "Medium",
            waterAmount = "80 ml",
            wateringTime = "17:00",
            wateringDays = "Mo",
            description = "Descript"
        ),
        Plant(
            id = 3,
            name = "Planta 4",
            plantSize = "Extra Large",
            waterAmount = "50 ml",
            wateringTime = "19:00",
            wateringDays = "We",
            photo = "",
            description = "Descript"
        ),
    )
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopStart,
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_header_background),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
    }
    Column(
        modifier = Modifier.padding(spacing.medium)
    ) {
        HeaderHomeScreen(
            modifier = Modifier
                .weight(1f)

                .fillMaxSize(),
        )
        BodyHomeScreen(
            modifier = Modifier
                .weight(4f)
                .fillMaxSize(),
            spacing = spacing,
            plants = listaPlantas,
            state = state,
            onCardLongClick = { plant ->
                onAction(HomeAction.OnCardLongClick(plant))
            }
        )
    }
}

@Composable
fun HeaderHomeScreen(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = stringResource(R.string.let_s_care_my_plants),
            modifier = Modifier.weight(0.5f),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        CustomFloatingActionButtonNotification(
            onClick = { /*TODO*/ },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.secondary,
            icon = Icons.Outlined.Notifications,
            isVisible = true,
            isNotify = true
        )
    }
}

@Composable
fun BodyHomeScreen(
    plants: List<Plant>,
    modifier: Modifier,
    state: HomeState,
    spacing: Spacing,
    onCardLongClick: (Plant) -> Unit
) {
    val showModal = remember { mutableStateOf(false) }
    DeletePlantConfirmationModal(
        showDialog = showModal,
        spacing = spacing,
        plant = state.plant,
        onConfirm = { },
        onCancel = { showModal.value = false }
    )
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TabViewv2(
                plants = plants,
                spacing = spacing,
                onLongClick = { plant ->
                    showModal.value = true
                    onCardLongClick(plant)
                }
            )
        }
    }
}

@Composable
fun TabViewv2(plants: List<Plant>, spacing: Spacing, onLongClick: (Plant) -> Unit) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.upcoming),
        stringResource(R.string.forgot_to_water),
        stringResource(R.string.history)
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        TabRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            selectedTabIndex = tabIndex,
            containerColor = Color.Transparent,
            divider = {},
            indicator = { tabPositions ->
                val tabSize = tabPositions.maxOf { it.right - it.left }
                val indicatorWidth = tabSize / 3f
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .padding(end = indicatorWidth)
                        .height(2.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = tabIndex == index
                Column(

                    modifier = Modifier
                        .padding(vertical = spacing.medium)
                        .clickable {
                            tabIndex = index
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxHeight(),
                        text = title,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

            }
        }

        when (tabIndex) {
            0 -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(top = spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(spacing.medium),
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                    content = {
                        items(plants) { itemPlant ->
                            CustomCardView(
                                titleCard = itemPlant.name,
                                subtitleCard = itemPlant.description,
                                imageCard = itemPlant.photo,
                                icon = R.drawable.home_card_icon_water,
                                wateringDaysLabel = itemPlant.wateringDays,
                                waterAmountLabel = itemPlant.waterAmount,
                                onClick = {},
                                onLongClick = {
                                    onLongClick(itemPlant)
                                }
                            )
                        }
                    })
            }

            1 -> Text(text = " ")
            2 -> Text(text = " ")
        }
    }
}


@Preview
@Composable
fun PreviewHomeScreen() {
    WaterMyPlantsTheme {
        HomeScreen(state = HomeState(), onAction = {})
    }
}