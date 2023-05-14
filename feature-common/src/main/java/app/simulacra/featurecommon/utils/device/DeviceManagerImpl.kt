package app.simulacra.featurecommon.utils.device

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import app.simulacra.domaincore.utils.device.DeviceManager
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber
import java.io.IOException
import java.util.*
import javax.inject.Inject

class DeviceManagerImpl @Inject constructor(private val context: Context) : DeviceManager {

    override fun getAdvertisingId(): String? {
        var adInfo: AdvertisingIdClient.Info? = null
        try {
            adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
        } catch (e: IOException) {
            Timber.e(
                e,
                "Unrecoverable error connecting to Google Play services (e.g.), the old version of the service doesn't support getting AdvertisingId"
            )
        } catch (e: GooglePlayServicesNotAvailableException) {
            Timber.e(e, "Google Play services is not available entirely!")
        } catch (e: GooglePlayServicesRepairableException) {
            Timber.e(
                e,
                "Thrown when Google Play Services is not installed, up-to-date, or enabled."
            )
        }

        return adInfo?.id
    }

    override fun getAppVersionName(): String {
        return context.packageManager.getPackageInfo(context.packageName, 0).versionName
    }

    override fun getAppVersionCode(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // Probably will fit into the int range
            context.packageManager.getPackageInfo(context.packageName, 0).longVersionCode.toInt()
        } else {
            @Suppress("DEPRECATION")
            context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        }
    }

    override fun getDeviceModel() = "${Build.MANUFACTURER} ${Build.MODEL}"

    @ExperimentalCoroutinesApi
    override suspend fun getFcmTokenFlow(): Flow<String> {
        return callbackFlow {
            FirebaseMessaging.getInstance().token
                .addOnSuccessListener {
                    flowOf(it)
                }
                .addOnCompleteListener {
                    emptyFlow<String>()
                }
                .addOnFailureListener {
                    Timber.e(it, "Failed to retrieve FCM token!")
                }
        }
    }

    @SuppressLint("HardwareIds")
    override fun getHardwareId(): String =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    override fun getOsDetails(): String {
        return "${Build.VERSION.RELEASE} (${Build.VERSION.SDK_INT}) ${Build.VERSION.CODENAME}"
    }

    override fun getOsVersionName(): String {
        return Build.VERSION.RELEASE
    }

    override fun getUUID(): String = UUID.randomUUID().toString()

    override fun getTimeZoneUTC(): Long = TimeZone.getDefault().rawOffset.toLong()

    override fun getLocale(): String = Locale.getDefault().language

}