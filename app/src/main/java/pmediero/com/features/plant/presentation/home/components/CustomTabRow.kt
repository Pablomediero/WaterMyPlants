package pmediero.com.features.plant.presentation.home.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.plant.presentation.home.model.TabType

@Composable
fun CustomTabRow(
    modifier: Modifier,
    selectedTabIndex: Int,
    containerColor: Color,
    contentColor: Color,
    textSelectedColor: Color,
    textUnSelectedColor: Color,
    tabs: List<Int>,
    onTabClick: (Int) -> Unit
) {
    val density = LocalDensity.current
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        containerColor = containerColor,
        contentColor = contentColor,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                )
            )
        }
    ) {
        tabs.forEachIndexed { tabIndex, resId ->
            val isSelected = selectedTabIndex == tabIndex
            val title = context.getString(resId)
//            Column(
//                modifier = Modifier
//                    .clickable {
//                        onTabClick(tabIndex)
//                    },
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(10.dp)
//            ) {
//                Text(
//                    modifier = Modifier.padding(spacing.medium),
//                    text = title ,
//                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
//                    style = MaterialTheme.typography.titleSmall,
//                    onTextLayout = { textLayoutResult ->
//                        tabWidths[tabIndex] =
//                            with(density) { textLayoutResult.size.width.toDp() }
//                    }
//                )
            Tab(
                modifier = Modifier.padding(horizontal = spacing.default),
                selected = isSelected,
                selectedContentColor = textSelectedColor,
                unselectedContentColor = textUnSelectedColor,
                onClick = { onTabClick(tabIndex) },
                text = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        onTextLayout = { textLayoutResult ->
                            tabWidths[tabIndex] =
                                with(density) { textLayoutResult.size.width.toDp() }
                        }
                    )
                }
            )
        }

    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

@Preview(device = "id:pixel_7")
@Composable
fun CustomTabRowPreview() {
    WaterMyPlantsTheme {
        var tabselected by remember {
            mutableStateOf(0)
        }
        CustomTabRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            selectedTabIndex = tabselected,
            tabs = TabType.entries.map { it.stringId },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            textSelectedColor = MaterialTheme.colorScheme.primary,
            textUnSelectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
            onTabClick = {}
        )
    }
}