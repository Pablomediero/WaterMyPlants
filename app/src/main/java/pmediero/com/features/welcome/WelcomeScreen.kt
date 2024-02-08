package pmediero.com.features.welcome
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
import pmediero.com.R
import pmediero.com.core_ui.WaterMyPlantsTheme

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp)

    ) {
        Column (
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(0.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_welcome),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds

                )

                Text(
                    text = "Welcome to Water My Plants!",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(16.dp)

                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Start your journey",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(12.dp)
            )
            Text(
                text = "There are no plants in the list, please add your first plant",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(12.dp)
            )
            Button(
                onClick = {  },
                modifier = Modifier
                    .padding(12.dp)
                    .width(200.dp)
                    .height(56.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
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

@Preview
@Composable
fun WelcomeScreenPreview() {
    WaterMyPlantsTheme {
        WelcomeScreen()
    }
}
