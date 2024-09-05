package pmediero.com.features.plant.domain.useCase

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pmediero.com.core.model.local.Plant
import pmediero.com.features.plant.domain.repository.PlantRepository
import pmediero.com.features.plant.presentation.notification.model.TabType
import java.time.LocalDate
import java.util.Locale

class GetFilteredNotificationsUseCase(
    private val plantRepository: PlantRepository

) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Flow<Map<TabType, List<Plant>>> {
        return plantRepository.observePlants().map { listAllPlants ->
            mapOf(
                TabType.TODAY to filterTodayShortedPlants(listAllPlants)
            )
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun filterTodayShortedPlants(listPlants: List<Plant>): List<Plant> {
        val currentDayOfWeek = LocalDate.now().dayOfWeek.name.substring(0, 2).lowercase(Locale.ROOT)
        return listPlants.filter { plant ->
            plant.wateringDays.split(" ").any { day ->
                day.equals("everyday", ignoreCase = true) || day.lowercase(Locale.ROOT) == currentDayOfWeek
            }
        }.sortedBy { it.wateringTime }
    }

}