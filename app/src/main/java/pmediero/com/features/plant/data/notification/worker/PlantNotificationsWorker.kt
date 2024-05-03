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
import pmediero.com.features.plant.data.notification.scheduler.NotificationScheduler
import pmediero.com.features.plant.data.repository.PlantRepository

class PlantNotificationsWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params), KoinComponent {
    private val plantRepository: PlantRepository by inject()
    private val notificationScheduler: NotificationScheduler by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return try {
            val allPlantsList: List<Plant> = plantRepository.getPlants()
            val filterPlants: List<Plant> = plantRepository.getFilterUpcomingPlants()
            doNotificationsWork(allPlantsList)
            doUpdateIsWateredPlantWork(filterPlants)
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

    private fun doNotificationsWork(filterPlants: List<Plant>) {

        Log.d("WorkerNotificationsPlants", "Notification ON")
        filterPlants.forEach { plant ->
            notificationScheduler(plant)
        }
        Log.d("WorkerNotificationsPlants", "Notification SUCCESS")

    }


}
