package pmediero.com.features.plant.data.work.scheduler

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import pmediero.com.core.presentation.util.setTimeToMillis
import pmediero.com.features.plant.data.work.worker.PlantUpdateWork
import java.util.Calendar
import java.util.concurrent.TimeUnit

class PlantUpdateScheduler(){
    fun scheduleWorkerPlantUpdate(context: Context) {
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
        val timeToUpdate = Calendar.getInstance().setTimeToMillis(23,59)
        return timeToUpdate - now.timeInMillis
    }
}


