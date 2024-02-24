package pmediero.com.core_ui

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
data class Density(
    val negativeThree: Dp = (-12).dp,
    val negativeTwo: Dp = (-8).dp,
    val negativeOne: Dp = (-4).dp,
    val zero: Dp = 0.dp,
    val positiveOne: Dp = 4.dp,
    val positiveTwo: Dp = 8.dp,
    val positiveThree: Dp = 12.dp,
)

val LocalDensity = compositionLocalOf { Density() }