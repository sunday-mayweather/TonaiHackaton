package app.simulacra.featurecommon.utils.notifications

import android.app.NotificationChannel
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

class NotificationsManager(
    context: Context,
    private val channelId: String,
    private val channelName: String,
    private val channelDescription: String
) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager

    @RequiresApi(Build.VERSION_CODES.O)
    fun createScreenRecorderNotificationChannel() {
        val importance = android.app.NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        channel.description = channelDescription
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        notificationManager.createNotificationChannel(channel)
    }
}