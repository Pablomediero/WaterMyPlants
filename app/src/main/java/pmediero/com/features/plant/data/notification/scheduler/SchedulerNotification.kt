package pmediero.com.features.plant.data.notification.scheduler

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
import pmediero.com.core.presentation.util.setTimeToMillis
import pmediero.com.features.plant.data.notification.receiver.NotificationReceiver
import java.util.Calendar

class SchedulerNotification(
) : KoinComponent {
    private val context: Context by inject()
    @SuppressLint("ScheduleExactAlarm")
    operator fun invoke(plant: Plant) {
        createChannel(context)
        Log.i("AlarmManager", "Notificacion ${plant.name}")
        val timeParts = plant.wateringTime.split(":")
        val timeUntilAlarm = Calendar.getInstance().setTimeToMillis(hour = timeParts[0].toInt(), minute = timeParts[1].toInt())
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
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }
}