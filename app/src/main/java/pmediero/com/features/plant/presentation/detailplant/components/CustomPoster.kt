package pmediero.com.features.plant.presentation.detailplant.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.WaterMyPlantsTheme

@Composable
fun CustomPoster(
    infoList: List<Pair<String, String>>,
    containerColor: Color,
    titleColor: Color,
    valueColor: Color,
    shape: Shape = FloatingActionButtonDefaults.shape,
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .clip(shape)
            .background(containerColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        infoList.forEachIndexed { index, (title, info) ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(spacing.small),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacing.extraSmall)
            ) {
                Text(text = title, style = MaterialTheme.typography.labelLarge, color = titleColor)
                Text(text = info, style = MaterialTheme.typography.bodySmall, color = valueColor)
            }
            if (index < infoList.size - 1) {

                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .padding(vertical = spacing.small),
                        color = Color.Gray
                    )

            }
        }
    }
}

@Preview
@Composable
fun CustomPosterPreview() {
    val infoList = listOf(
        "Size" to "Mediumssss",
        "WaterAmount" to "250 ml",
        "Time" to "22:00"
    )
    WaterMyPlantsTheme {
        CustomPoster(
            infoList = infoList,
            containerColor = Color.White,
            titleColor = Color.Black,
            valueColor = Color.Black,
            shape = MaterialTheme.shapes.small
        )
    }
}