package pmediero.com.features.plant.data.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import pmediero.com.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val plantId = intent?.getIntExtra("plantId", 0) ?: 0
        val plantName = intent?.getStringExtra("plantName") ?: "Plant"
        createSimpleNotification(context, plantName, plantId)
    }

    private fun createSimpleNotification(context: Context, plantName: String, plantId: Int) {
        val notification = NotificationCompat.Builder(context, "myChannel")
            .setSmallIcon(R.drawable.home_card_icon_water)
            .setContentTitle("Its Water Time!")
            .setContentText("It's time to water the $plantName plant")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(plantId, notification)
    }
}
