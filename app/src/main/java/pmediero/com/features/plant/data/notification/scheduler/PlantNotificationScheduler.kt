package pmediero.com.features.plant.data.notification.scheduler

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import pmediero.com.core.presentation.util.setTimeToMillis
import pmediero.com.features.plant.data.notification.worker.PlantNotificationsWork
import java.util.Calendar
import java.util.concurrent.TimeUnit

class PlantNotificationScheduler() {

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
        val timeToUpdate = Calendar.getInstance().setTimeToMillis(0,1)
        Log.i("WorkerNotificationsPlants","Time to init worke: ${timeToUpdate - now.timeInMillis}")
        return timeToUpdate - now.timeInMillis
    }
}