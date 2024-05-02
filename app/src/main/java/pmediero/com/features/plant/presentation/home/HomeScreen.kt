package pmediero.com.features.plant.presentation.home


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import pmediero.com.R
import pmediero.com.core.model.local.Plant
import pmediero.com.core.presentation.components.CustomFloatingActionButtonNotification
import pmediero.com.core.presentation.components.DeletePlantConfirmationModal
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.plant.presentation.home.components.CustomCardView
import pmediero.com.features.plant.presentation.home.components.CustomTabRow
import pmediero.com.features.plant.presentation.home.model.TabType
import pmediero.com.features.plant.presentation.home.root.HomeAction
import pmediero.com.features.plant.presentation.home.root.HomeState
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
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
            onNotifyClick = {
                onAction(HomeAction.NavigateAddPlant)
            },

        )

        BodyHomeScreen(
            modifier = Modifier
                .weight(4f)
                .fillMaxSize(),
            spacing = spacing,
            state = state,
            plants = state.plantListMap[state.tabSelected] ?: emptyList(),
            onTabClicked = { index ->
                onAction(HomeAction.OnTabClicked(index))
            },
            onIconClicked = { plant ->
                onAction(HomeAction.OnIconCardPlantClicked(plant))
            },
            onCardClick = { ipPlantParam ->
                onAction(HomeAction.OnClickPlant(ipPlantParam))
            },
            onCardLongClick = { plant ->
                onAction(HomeAction.OnCardLongClick(plant))
            }
        )

    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HeaderHomeScreen(modifier: Modifier, onNotifyClick: () -> Unit) {
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
            onClick = {
                onNotifyClick()
            },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.secondary,
            icon = Icons.Outlined.Notifications,
            isVisible = true,
            isNotify = true
        )
        CustomFloatingActionButtonNotification(
            onClick = {

            },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.secondary,
            icon = Icons.Outlined.AccountCircle,
            isVisible = true,
            isNotify = true
        )
    }
}

@Composable
fun BodyHomeScreen(
    modifier: Modifier,
    state: HomeState,
    spacing: Spacing,
    plants: List<Plant>,
    onTabClicked: (Int) -> Unit,
    onIconClicked: (Plant) -> Unit,
    onCardClick: (String) -> Unit,
    onCardLongClick: (Plant) -> Unit
) {
    val showModal = remember { mutableStateOf(false) }
    DeletePlantConfirmationModal(
        showDialog = showModal,
        itemName = state.plant.name,
        onConfirm = { },
        onCancel = { showModal.value = false }
    )
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            CustomTabRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                selectedTabIndex = state.tabSelected.ordinal,
                tabs = TabType.entries.map { it.stringId },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
                textSelectedColor = MaterialTheme.colorScheme.primary,
                textUnSelectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                onTabClick = { index -> onTabClicked(index) }
            )
        }
        if (plants.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.all_plants_have_been_watered),
                    modifier = Modifier.padding(top = spacing.large),
                    style = MaterialTheme.typography.titleSmall.copy(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
        }
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
                        icon = if (!itemPlant.isWatered) R.drawable.home_card_icon_water else Icons.Filled.Check,
                        labelCard = listOf(itemPlant.waterAmount, itemPlant.wateringDays),
                        onClick = {
                            onCardClick(itemPlant.id)
                        },
                        onIconClicked = {
                            onIconClicked(itemPlant)
                        },
                        onLongClick = {
                            showModal.value = true
                            onCardLongClick(itemPlant)
                        }
                    )
                }
            })

    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun PreviewHomeScreen() {
    WaterMyPlantsTheme {
        HomeScreen(state = HomeState(), onAction = {})
    }
}