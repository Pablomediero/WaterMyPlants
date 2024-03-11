package pmediero.com.features.welcome.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pmediero.com.R
import pmediero.com.core.presentation.common.CustomFloatingActionButtonWithText
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.Spacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.core_ui.onBackgroundVariant
import pmediero.com.features.welcome.action.WelcomeAction

@Composable
fun WelcomeScreen(
    onEvent: (WelcomeAction) -> Unit,
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = spacing.default)
    ) {
        HeaderWelcomeScreen(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(spacing.default)
        )
        BodyWelcomeScreen(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            spacing = spacing,
            onEvent = onEvent,
        )
    }
}

@Composable
fun HeaderWelcomeScreen(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.welcome_header_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds

        )

        Text(
            text = stringResource(R.string.welcome_to_water_my_plants),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun BodyWelcomeScreen(modifier: Modifier, spacing: Spacing, onEvent: (WelcomeAction) -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            spacing.default,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                spacing.default,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_body_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds

            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    horizontal = spacing.large,
                    vertical = spacing.default
                ),
            verticalArrangement = Arrangement.spacedBy(
                spacing.default,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.start_your_journey),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(R.string.there_are_no_plants_in_the_list_please_add_your_first_plant),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackgroundVariant
            )
            Column(
                modifier = Modifier.padding(all = spacing.large),
                verticalArrangement = Arrangement.spacedBy(
                    spacing.small,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                CustomFloatingActionButtonWithText(
                    modifier = Modifier.padding(all = spacing.default),
                    onClick = {
                        onEvent(WelcomeAction.OnAddFirstPlantClick)
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    icon = Icons.Default.Add,
                    text = stringResource(R.string.add_your_first_plant)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewWelcomeCompose() {
    WaterMyPlantsTheme {
        WelcomeScreen() {}
    }
}