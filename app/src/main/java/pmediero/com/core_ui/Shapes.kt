package pmediero.com.core_ui
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    extraSmall = RoundedCornerShape(
        topStart = 4.dp,
        topEnd = 4.dp,
        bottomStart = 4.dp,
        bottomEnd = 4.dp
    ),
    small = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 8.dp,
        bottomStart = 8.dp,
        bottomEnd = 8.dp
    ),
    medium = RoundedCornerShape(
        topStart = 12.dp,
        topEnd = 12.dp,
        bottomStart = 12.dp,
        bottomEnd = 12.dp
    ),
    large = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomStart = 16.dp,
        bottomEnd = 16.dp
    ),
    extraLarge = RoundedCornerShape(
        topStart = 28.dp,
        topEnd = 28.dp,
        bottomStart = 28.dp,
        bottomEnd = 28.dp
    )
)