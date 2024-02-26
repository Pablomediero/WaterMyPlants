package pmediero.com.features.welcome.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pmediero.com.R
import pmediero.com.core_ui.LocalSpacing
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.core.navigation.AppRoutes

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = LocalSpacing.current.default)
    ) {
        HeaderWelcomeScreen(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(LocalSpacing.current.default)
        )
        BodyWelcomeScreen(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            navController = navController
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
            text = "Welcome to Water My Plants!",
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun BodyWelcomeScreen(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            LocalSpacing.current.default,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                LocalSpacing.current.default,
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
                    horizontal = LocalSpacing.current.large,
                    vertical = LocalSpacing.current.default
                ),
            verticalArrangement = Arrangement.spacedBy(
                LocalSpacing.current.default,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Start your journey",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "There are no plants in the list, please add your first plant",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
            Column(
                modifier = Modifier.padding(all = LocalSpacing.current.large),
                verticalArrangement = Arrangement.spacedBy(
                    LocalSpacing.current.small,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(AppRoutes.AddPlantScreen.route)
                    },
                    modifier = Modifier
                        .padding(all = LocalSpacing.current.default)
                        .width(200.dp)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Add my first plant")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewWelcomeCompose() {
    WaterMyPlantsTheme {

        val navController = rememberNavController()
        WelcomeScreen(navController)
    }
}