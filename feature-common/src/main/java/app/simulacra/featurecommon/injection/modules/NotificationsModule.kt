package app.simulacra.featurecommon.injection.modules

import android.app.Notification
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import app.simulacra.featurecommon.R
import app.simulacra.featurecommon.injection.scopes.ServiceScope
import app.simulacra.featurecommon.utils.notifications.NotificationsManager
import dagger.Module
import dagger.Provides

@Module
class NotificationsModule {

    companion object {
        private const val SR_CHANNEL_ID = "simulacra.app.screen-recorder"
        private const val SR_CHANNEL_NAME = "Screen recorder notification"
        private const val SR_CHANNEL_DESCRIPTION = "Used for displaying notification that screen has been recorded"
    }

    @Provides
    @ServiceScope
    fun providesScreenRecorderNotificationChannel(context: Context): NotificationsManager {
        return NotificationsManager(context = context, channelId = SR_CHANNEL_ID, channelName =
        SR_CHANNEL_NAME, channelDescription = SR_CHANNEL_DESCRIPTION)
    }

    @Provides
    @ServiceScope
    fun provideScreenRecorderNotificationBitmap(context: Context): Bitmap {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_app_logo)
        return drawable!!.toBitmap()
    }

    @Provides
    @ServiceScope
    fun providesScreenRecorderNotification(context: Context, notificationsManager: NotificationsManager, notificationBitmap: Bitmap): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationsManager.createScreenRecorderNotificationChannel()
        }
        return NotificationCompat.Builder(context, SR_CHANNEL_ID)
            .setContentTitle(context.getString(R.string.screen_recorder_notification_title))
            .setContentText(context.getString(R.string.screen_recorder_notification_active))
            .setLargeIcon(notificationBitmap)
            .build()
    }
}