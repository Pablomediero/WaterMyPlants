package pmediero.com.features.plant.domain.useCase

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pmediero.com.core.model.local.Plant
import pmediero.com.features.plant.domain.repository.PlantRepository
import pmediero.com.features.plant.presentation.home.model.TabType
import java.time.LocalDate
import java.util.Locale

class GetFilteredPlantsUseCase(
    private val plantRepository: PlantRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Flow<Map<TabType, List<Plant>>> {
        return plantRepository.observePlants().map { listAllPlants ->
            mapOf(
                TabType.HISTORY to listAllPlants,
                TabType.UPCOMING to plantRepository.getFilterUpcomingPlants(),
                TabType.FORGOT_TO_WATER to filterForgotToWaterPlants(listAllPlants)
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun filterForgotToWaterPlants(plants: List<Plant>): List<Plant> {
        val yesterday = LocalDate.now().minusDays(1).dayOfWeek.name.substring(0, 2).lowercase(Locale.ROOT)
        return plants.filter { plant ->
            plant.wateringDays.split(" ").any { day ->
                day.lowercase(Locale.ROOT) == yesterday || day.equals("everyday", ignoreCase = true)
            }
        }.filter { plant ->
            plant.lastWateredDate.lowercase(Locale.ROOT) == yesterday
        }
    }

}

