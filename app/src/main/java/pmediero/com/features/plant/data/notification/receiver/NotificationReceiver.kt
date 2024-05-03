package pmediero.com.features.plant.data.notification.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import pmediero.com.R
import pmediero.com.navigation.MainActivity

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationId = intent?.getIntExtra("notificationId", 0) ?: 0
        val plantIdParam = intent?.getStringExtra("plantId") ?: "Plant"
        val plantName = intent?.getStringExtra("plantName") ?: "Plant"
        createSimpleNotification(context, plantName, plantIdParam ,notificationId)
    }



    private fun createSimpleNotification(context: Context, plantName: String, plantIdParam :String, notificationId: Int) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            "watermyplants://detail/$plantIdParam".toUri(),
            context,
            MainActivity::class.java
        )
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(notificationId, PendingIntent.FLAG_IMMUTABLE)
        }
        val notification = NotificationCompat.Builder(context, "myChannel")
            .setSmallIcon(R.drawable.home_card_icon_water)
            .setContentTitle("Its Water Time!")
            .setContentText("It's time to water the $plantName plant")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationId, notification)
    }
}
