package pmediero.com.features.plant.data.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pmediero.com.core.model.local.Plant
import pmediero.com.core.presentation.util.calculateTimeLog
import pmediero.com.core.presentation.util.setTimeToMillis
import pmediero.com.features.plant.data.notification.receiver.NotificationReceiver
import java.util.Calendar

class PlantNotificationSchedulerImpl(
) : PlantNotificationScheduler, KoinComponent {
    private val context: Context by inject()
    @SuppressLint("ScheduleExactAlarm")
    override fun schedulerNotification(plant: Plant) {
        //createChannel(context)
        val now = Calendar.getInstance()
        val timeParts = plant.wateringTime.split(":")
        val timeUntilAlarm = Calendar.getInstance().setTimeToMillis(hour = timeParts[0].toInt(), minute = timeParts[1].toInt())

        val timeDifferenceInMillis = timeUntilAlarm - now.timeInMillis
        timeDifferenceInMillis.calculateTimeLog("WorkerPlantsNotifications", "Scheduler ${plant.name} Notification")
        Log.i("WorkerPlantsNotifications", "Time difference in milliseconds: $timeDifferenceInMillis")


        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("notificationId", plant.id.hashCode())
            putExtra("plantId", plant.id)
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

    override fun cancelNotification(plant: Plant) {
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("notificationId", plant.id.hashCode())
            putExtra("plantId", plant.id)
            putExtra("plantName", plant.name)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            plant.id.hashCode(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
        Log.i("WorkerPlantsNotificationsIndividual","Individual Notification: CANCEL")
    }

    override fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "myChannel",
                "WaterMyPlants",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = ""
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }


}