package pmediero.com.features.plant.domain.useCase

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.local.Plant
import pmediero.com.features.plant.presentation.home.model.TabType
import java.time.LocalDate
import java.util.Locale

class GetFilteredPlantsUseCase(
    private val plantRepository: PlantRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Flow<Map<TabType, List<Plant>>> {
        return plantRepository.observePlants().map { listAllPlants ->
            Log.i("BBDDRESULT", "After ${listAllPlants[0].id}")
            mapOf(
                TabType.HISTORY to listAllPlants,
                TabType.UPCOMING to filterUpcomingPlants(listAllPlants),
                TabType.FORGOT_TO_WATER to filterForgotToWaterPlants(listAllPlants)
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun filterUpcomingPlants(listPlants: List<Plant>): List<Plant> {
        val currentDayOfWeek = LocalDate.now().dayOfWeek.name.substring(0, 2).lowercase(Locale.ROOT)
        return listPlants.filter { plant ->
            plant.wateringDays.split(" ").any { day ->
                Log.i("Filtromapplants", "$day y $currentDayOfWeek")
                day.equals("everyday", ignoreCase = true) || day.lowercase(Locale.ROOT) == currentDayOfWeek
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun filterForgotToWaterPlants(listPlants: List<Plant>): List<Plant> {
        val yesterday = LocalDate.now().minusDays(1).dayOfWeek.name.substring(0, 2).lowercase(Locale.ROOT)
        return listPlants.filter { plant ->
            plant.wateringDays.split(" ").any { day ->
                day.equals("everyday", ignoreCase = true) || day.lowercase(Locale.ROOT) == yesterday
            }
        }.filter { plant ->
            !plant.isWatered
        }
    }

}

