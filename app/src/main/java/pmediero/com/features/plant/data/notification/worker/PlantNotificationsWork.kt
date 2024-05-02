package pmediero.com.features.plant.data.notification.worker

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pmediero.com.core.model.local.Plant
import pmediero.com.features.plant.data.notification.scheduler.ScheduleNotificationPlant
import pmediero.com.features.plant.data.repository.PlantRepository
import java.time.LocalDate
import java.util.Locale

class PlantNotificationsWork(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params), KoinComponent {
    private val plantRepository: PlantRepository by inject()
    private val scheduleNotificationPlant: ScheduleNotificationPlant by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return try {
            val allPlantsList: List<Plant> = plantRepository.getPlants()
            val todayPlantsToWater: List<Plant> = filterUpcomingPlants(allPlantsList)
            for (plant in todayPlantsToWater) {
                scheduleNotificationPlant(plant)
            }
            Result.success()
        } catch (e: Exception) {
            Log.d("WorkerNotificationsPlants", "exception in doWork ${e.message}")
            Result.failure()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun filterUpcomingPlants(listPlants: List<Plant>): List<Plant> {
        val currentDayOfWeek = LocalDate.now().dayOfWeek.name.substring(0, 2).lowercase(Locale.ROOT)
        return listPlants.filter { plant ->
            plant.wateringDays.split(" ").any { day ->
                day.equals(
                    "everyday",
                    ignoreCase = true
                ) || day.lowercase(Locale.ROOT) == currentDayOfWeek
            }
        }
    }
}
