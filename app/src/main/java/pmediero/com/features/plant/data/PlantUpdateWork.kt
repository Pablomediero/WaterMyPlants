package pmediero.com.features.plant.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.local.Plant

class PlantUpdateWork(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params), KoinComponent {
    private val plantRepository: PlantRepository by inject()
    override suspend fun doWork(): Result {

        return try {
            Log.d("WorkerUpdatePlant", "Run work")
            val plantsToUpdate: List<Plant> = plantRepository.getPlants()
            Log.d("WorkerUpdatePlant", "Get Ready...")
            val updatedPlants = plantsToUpdate.map { plant ->
                plant.copy(isWatered = false)
            }
            Log.d("WorkerUpdatePlant", "Update isWatered...")
            plantRepository.saveAllPlant(updatedPlants)
            Log.d("WorkerUpdatePlant", "Run Completed")

            Result.success()
        } catch (e: Exception) {
            Log.d("WorkerUpdatePlant", "exception in doWork ${e.message}")
            Result.failure()
        }
    }
}
