package pmediero.com.core_ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = primary80,
    onPrimary = primary20,
    primaryContainer = primary30,
    onPrimaryContainer = primary90,
    inversePrimary = primary40,
    secondary = secondary80,
    onSecondary = secondary20,
    secondaryContainer = secondary30,
    onSecondaryContainer = secondary90,
    tertiary = tertiary80,
    onTertiary = tertiary20,
    tertiaryContainer = tertiary30,
    onTertiaryContainer = tertiary90,
    background = neutral6,
    onBackground = neutral90,
    surface = neutral6,
    onSurface = neutral90,
    surfaceVariant = neutralVariant30,
    onSurfaceVariant = neutralVariant80,
    surfaceTint = primary80,
    inverseSurface = neutral90,
    inverseOnSurface = neutral20,
    error = error80,
    onError = error20,
    errorContainer = error30,
    onErrorContainer = error90,
    outline = neutralVariant60,
    outlineVariant = neutralVariant30,
    scrim = neutral0
)

private val LightColorScheme = lightColorScheme(
    primary = primary40,
    onPrimary = primary100,
    primaryContainer = primary90,
    onPrimaryContainer = primary10,
    inversePrimary = primary80,
    secondary = secondary40,
    onSecondary = secondary100,
    secondaryContainer = secondary90,
    onSecondaryContainer = secondary10,
    tertiary = tertiary40,
    onTertiary = tertiary100,
    tertiaryContainer = tertiary90,
    onTertiaryContainer = tertiary10,
    background = neutral98,
    onBackground = neutral10,
    surface = neutral98,
    onSurface = neutral10,
    surfaceVariant = neutralVariant90,
    onSurfaceVariant = neutralVariant30,
    surfaceTint = primary40,
    inverseSurface = neutral20,
    inverseOnSurface = neutral95,
    error = error40,
    onError = error100,
    errorContainer = error90,
    onErrorContainer = error10,
    outline = neutralVariant50,
    outlineVariant = neutralVariant80,
    scrim = neutral0
)

val ColorScheme.onBackgroundVariant: Color
    @Composable
    get() = if (isSystemInDarkTheme()) neutral70 else neutral40

@Composable
fun WaterMyPlantsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Dynamic color is available on Android 12+
    val supportsDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = when {
        supportsDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        supportsDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = Typography,
        content = content
    )
}