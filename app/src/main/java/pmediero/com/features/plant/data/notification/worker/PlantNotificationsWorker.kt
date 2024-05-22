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
import pmediero.com.features.plant.data.notification.PlantNotificationScheduler
import pmediero.com.features.plant.domain.repository.PlantRepository

class PlantNotificationsWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params), KoinComponent {
    private val plantRepository: PlantRepository by inject()
    private val plantNotificationScheduler: PlantNotificationScheduler by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return try {
            val allPlantsList: List<Plant> = plantRepository.getPlants()
            val filterPlants: List<Plant> = plantRepository.getFilterUpcomingPlants()
            Log.i("WorkerPlantsNotifications", "Worker Update: All Plants: ${allPlantsList.map { it.name }}}")
            Log.i("WorkerPlantsNotifications", "Worker Notification: Today Plants: ${filterPlants.map { it.name }}")
            doUpdateIsWateredPlantWork(filterPlants)
            if(filterPlants.isNotEmpty()){
                scheduleNotifications(allPlantsList)
            }

            Result.success()
        } catch (e: Exception) {
            Log.d("WorkerPlantsNotifications", "exception in doWork ${e.message}")
            Result.failure()
        }
    }

    private suspend fun doUpdateIsWateredPlantWork(allPlantsList: List<Plant>) {
        Log.d("WorkerPlantsUpdate", "Entra doUpdateWork")
        val updatedPlants = allPlantsList.map { plant ->
            plant.copy(isWatered = false)
        }
        plantRepository.saveAllPlant(updatedPlants)
        Log.d("WorkerPlantsUpdate", "Update SUCCESS")
    }

    private fun scheduleNotifications(filterPlants: List<Plant>) {
        filterPlants.forEach { plant ->
            plantNotificationScheduler.schedulerNotification(plant)
            Log.i("WorkerPlantsNotifications", "Worker Notification: Schedule Notification for ${plant.name}")
        }
        Log.i("WorkerPlantsNotifications", "Worker Notification: SUCCESS")

    }


}
