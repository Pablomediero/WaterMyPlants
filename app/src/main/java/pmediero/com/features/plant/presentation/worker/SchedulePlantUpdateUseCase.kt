package pmediero.com.features.plant.presentation.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import pmediero.com.features.plant.data.PlantUpdateWork
import java.util.Calendar
import java.util.concurrent.TimeUnit

class SchedulePlantUpdateUseCase(){
    fun schedulePlantUpdate(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<PlantUpdateWork>(1, TimeUnit.DAYS)
            .setInitialDelay(timeToUpdateWateredPlants(), TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "plant_update_work",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )
    }

    private fun timeToUpdateWateredPlants(): Long {
        val now = Calendar.getInstance()
        val timeToUpdate = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return timeToUpdate.timeInMillis - now.timeInMillis
    }
}


