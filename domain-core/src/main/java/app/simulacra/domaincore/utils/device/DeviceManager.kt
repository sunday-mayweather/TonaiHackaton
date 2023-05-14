package app.simulacra.domaincore.utils.device

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * Wrapper upon class which provides basic information about the device on which the application
 * is running.
 */
interface DeviceManager {

    fun getAdvertisingId(): String?

    fun getAppVersionName(): String

    fun getAppVersionCode(): Int

    fun getDeviceModel(): String

    @ExperimentalCoroutinesApi
    suspend fun getFcmTokenFlow(): Flow<String>

    fun getHardwareId(): String

    fun getOsDetails(): String

    fun getOsVersionName(): String

    fun getUUID(): String

    fun getTimeZoneUTC(): Long

    fun getLocale(): String

}