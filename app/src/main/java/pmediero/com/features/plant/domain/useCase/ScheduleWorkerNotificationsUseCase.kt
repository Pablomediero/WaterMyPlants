package pmediero.com.features.plant.domain.useCase

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import pmediero.com.features.plant.data.worker.PlantNotificationsWork
import java.util.Calendar
import java.util.concurrent.TimeUnit

class ScheduleWorkerNotificationsUseCase() {

    fun scheduleWorkerNotifications(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<PlantNotificationsWork>(1, TimeUnit.DAYS)
            .setInitialDelay(timeToUpdateNotifications(), TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "plant_notifications_work",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )
    }

    private fun timeToUpdateNotifications(): Long {
        val now = Calendar.getInstance()
        val timeToUpdate = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 1)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        Log.i("WorkerNotificationsPlants","Time to init worke: ${timeToUpdate.timeInMillis - now.timeInMillis}")

        return timeToUpdate.timeInMillis - now.timeInMillis
    }
}