package pmediero.com.features.plant.presentation.notification

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.R
import pmediero.com.core.model.local.Plant
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.plant.presentation._common.CustomIconButtonDefault
import pmediero.com.features.plant.presentation.home.components.CustomTabRow
import pmediero.com.features.plant.presentation.notification.components.Item
import pmediero.com.features.plant.presentation.notification.model.TabType
import pmediero.com.features.plant.presentation.notification.root.NotificationAction
import pmediero.com.features.plant.presentation.notification.root.NotificationState

@Composable
fun NotificationScreen(
    state: NotificationState,
    onAction: (NotificationAction) -> Unit
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDFF0DC)),
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
    ) {
        HeaderHomeScreen(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .padding(horizontal = spacing.medium, vertical = spacing.large),
            spacing = spacing,
            onReturnButtonClick = {
                onAction(NotificationAction.OnReturnClick)
            },
        )

        BodyHomeScreen(
            modifier = Modifier
                .weight(8f)
                .fillMaxWidth(),
            spacing = spacing,
            isLoading = state.isLoading,
            plants = state.plantListMap[TabType.TODAY] ?: emptyList(),
            onLinkTextClick = { plantIdParam ->
                onAction(NotificationAction.OnLinkTextClick(plantIdParam))
            }
        )

    }
}

@Composable
fun HeaderHomeScreen(modifier: Modifier, spacing: Spacing, onReturnButtonClick: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            spacing.small,
            Alignment.CenterVertically
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                CustomIconButtonDefault(
                    onClick = {
                        onReturnButtonClick()
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
            }

            Column(
                modifier = Modifier.weight(5f)
            ) {
                Text(
                    text = stringResource(R.string.notifications),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

        }

    }


}

@Composable
fun BodyHomeScreen(
    modifier: Modifier,
    spacing: Spacing,
    isLoading: Boolean,
    plants: List<Plant>,
    onLinkTextClick: (String) -> Unit
) {

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spacing.small),
            horizontalArrangement = Arrangement.Start
        ) {
            CustomTabRow(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .background(Color.Transparent),
                selectedTabIndex = 0,
                tabs = TabType.entries.map { it.stringId },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
                textSelectedColor = MaterialTheme.colorScheme.primary,
                textUnSelectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                onTabClick = { index -> }
            )
        }
        Column(
            modifier = modifier
                .padding(top = spacing.small)
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
            verticalArrangement = Arrangement.spacedBy(spacing.medium, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Today",
                style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.onSurfaceVariant)
            )
            if (isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(top = spacing.large),
                    )
                }
            }
            if (plants.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.not_notifications_for_today),
                        modifier = Modifier.padding(top = spacing.large),
                        style = MaterialTheme.typography.titleSmall.copy(MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }
            }
            LazyColumn {
                items(plants) { plant ->
                    Item(
                        image = plant.photo,
                        title = plant.name,
                        subtitle = "Plant will need to be watered at ${plant.wateringTime}",
                        linkText = "Go to the plant",
                        onLinkTextClick = {
                            onLinkTextClick(plant.id)
                        },
                    )
                }
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun PreviewNotificationScreen() {
    WaterMyPlantsTheme {
        NotificationScreen(state = NotificationState()) {}
    }
}