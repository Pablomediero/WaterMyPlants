package pmediero.com.features.home.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow
import pmediero.com.core.model.realm.PlantEntity
import pmediero.com.features.home.presentation.root.HomeAction
import pmediero.com.features.home.presentation.root.OnLoadingHome

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onEvent: (HomeAction) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(OnLoadingHome)
    }

    val plants: StateFlow<List<PlantEntity>> = viewModel.plants

    if (plants.collectAsState().value.isNotEmpty()) {
        PlantInfo(plants)
    } else {
       Column {
           Text(text = "No hay ninguna planta en la bbdd")
       }
    }

}

@Composable
fun PlantInfo(plants: StateFlow<List<PlantEntity>>) {
    val plantList by plants.collectAsState(emptyList())

    if (plantList.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(plantList) { plant ->
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
    plant: PlantEntity,
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
        Text(text = "Días de riego: ${plant.wateringDays}")
        Text(text = "Hora de riego: ${plant.wateringTime}")
        Text(text = "Cantidad de agua: ${plant.waterAmount}")
        Text(text = "Tamaño de la planta: ${plant.plantSize}")
        Text(text = "Descripcion de la planta: ${plant.description}")
        Divider()
    }
}
