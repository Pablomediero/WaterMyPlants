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
import pmediero.com.features.plant.data.notification.scheduler.SchedulerNotification
import pmediero.com.features.plant.data.repository.PlantRepository
import java.time.LocalDate
import java.util.Locale

class PlantNotificationsWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params), KoinComponent {
    private val plantRepository: PlantRepository by inject()
    private val schedulerNotification: SchedulerNotification by inject()

    override suspend fun doWork(): Result {
        return try {
            val allPlantsList: List<Plant> = plantRepository.getPlants()
            doNotificationsWork(allPlantsList)
            doUpdateIsWateredPlantWork(allPlantsList)
            Result.success()
        } catch (e: Exception) {
            Log.d("WorkerNotificationsPlants", "exception in doWork ${e.message}")
            Result.failure()
        }
    }

    private suspend fun doUpdateIsWateredPlantWork(allPlantsList: List<Plant>) {
        Log.d("WorkerNotificationsPlants", "Update On")
        val updatedPlants = allPlantsList.map { plant ->
            plant.copy(isWatered = false)
        }
        plantRepository.saveAllPlant(updatedPlants)
        Log.d("WorkerNotificationsPlants", "Update SUCCESS")
    }

    private fun doNotificationsWork(allPlantsList: List<Plant>) {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           Log.d("WorkerNotificationsPlants", "Notification ON")
           val todayPlantsToWater: List<Plant> = filterUpcomingPlants(allPlantsList)
           todayPlantsToWater.forEach { plant ->
               schedulerNotification(plant)
           }
           Log.d("WorkerNotificationsPlants", "Notification SUCCESS")
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
