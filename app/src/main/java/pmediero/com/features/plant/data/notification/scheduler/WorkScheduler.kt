package pmediero.com.features.plant.data.notification.scheduler

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import pmediero.com.core.presentation.util.calculateTimeLog
import pmediero.com.core.presentation.util.setTimeToMillis
import pmediero.com.features.plant.data.notification.worker.PlantNotificationsWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

class WorkScheduler() {
    fun scheduleWorkerNotifications(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<PlantNotificationsWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(timeToUpdateNotifications(), TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "plant_notifications_work",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )
    }

    private fun timeToUpdateNotifications(): Long {
        Log.i("WorkerPlantsNotifications", "START APP...")
        val now = Calendar.getInstance()
        val timeToUpdate = Calendar.getInstance().apply {
            setTimeToMillis(hour = 0, minute = 0)
            if (before(now)) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        val timeDifferenceInMillis = timeToUpdate.timeInMillis - now.timeInMillis
        timeDifferenceInMillis.calculateTimeLog("WorkerPlantsNotifications", "Worker Launch")

        return timeToUpdate.timeInMillis - now.timeInMillis
    }
}