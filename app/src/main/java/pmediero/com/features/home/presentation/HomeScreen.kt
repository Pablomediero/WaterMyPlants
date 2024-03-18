package pmediero.com.features.home.presentation


import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import pmediero.com.core.model.local.Plant
import pmediero.com.features.home.presentation.root.HomeAction
import pmediero.com.features.home.presentation.root.HomeState

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {

   // val plants: StateFlow<List<Plant>> = viewModel.plants

    if (state.plantList.isNotEmpty()) {
        PlantInfo(state.plantList)
    } else {
        Column {
            Text(text = "No hay ninguna planta en la bbdd")
        }
    }

}

@Composable
fun PlantInfo(plants: List<Plant>) {

    if (plants.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(plants) { plant ->
                PlantItem(plant = plant)
            }
        }
    } else {
        Column {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun PlantItem(
    plant: Plant,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = plant.name,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(text = "ID: ${plant.id}")
        Text(text = "Días de riego: ${plant.wateringDays}")
        Text(text = "Hora de riego: ${plant.wateringTime}")
        Text(text = "Cantidad de agua: ${plant.waterAmount}")
        Text(text = "Tamaño de la planta: ${plant.plantSize}")
        Text(text = "Descripcion de la planta: ${plant.description}")
        AsyncImage(
            model = Uri.parse(plant.photo),
            contentDescription = "image",
            modifier = Modifier,
            contentScale = ContentScale.FillBounds
        )
        Log.i("ImagenScreen","Foto: ${plant.photo}")
        Divider()
    }
}
