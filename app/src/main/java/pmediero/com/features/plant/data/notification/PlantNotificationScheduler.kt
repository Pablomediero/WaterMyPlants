package pmediero.com.features.plant.data.notification

import android.content.Context
import pmediero.com.core.model.local.Plant

interface PlantNotificationScheduler {
    fun createChannel(context: Context)
    fun schedulerNotification(plant: Plant)
    fun cancelNotification(plant: Plant)
}