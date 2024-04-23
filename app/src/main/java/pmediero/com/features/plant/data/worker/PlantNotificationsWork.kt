package pmediero.com.features.plant.data.worker

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.local.Plant
import pmediero.com.features.plant.data.notification.NotificationReceiver
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class PlantNotificationsWork(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params), KoinComponent {
    private val plantRepository: PlantRepository by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return try {
            val allPlantsList: List<Plant> = plantRepository.getPlants()
            val todayPlantsToWater: List<Plant> = filterUpcomingPlants(allPlantsList)
            scheduleNotifications(applicationContext, todayPlantsToWater)
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


    @SuppressLint("ScheduleExactAlarm")
    private fun scheduleNotifications(context: Context, todayPlantsToWater: List<Plant>) {
        createChannel(context)
        for (plant in todayPlantsToWater) {
            scheduleNotification(context, plant)
        }
    }



    @SuppressLint("ScheduleExactAlarm")
    private fun scheduleNotification(context: Context, plant: Plant) {
        val timeParts = plant.wateringTime.split(":")
        val timeUntilAlarm = timeToUpdateNotifications(hour = timeParts[0].toInt(), minute = timeParts[1].toInt() )
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("plantId", plant.id.hashCode())
            putExtra("plantName", plant.name)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            plant.id.hashCode(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeUntilAlarm,
            pendingIntent
        )
    }

    private fun timeToUpdateNotifications(hour: Int, minute: Int): Long {
        val timeWaterPlant = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return timeWaterPlant.timeInMillis
    }

    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "myChannel",
                "WaterMyPlants",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = ""
            }

            val notificationManager: NotificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }


}
