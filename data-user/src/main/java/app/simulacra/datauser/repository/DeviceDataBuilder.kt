package app.simulacra.datauser.repository

import app.simulacra.datauser.rest.models.request.DeviceRequestModel
import app.simulacra.domaincore.utils.device.DeviceManager
import app.simulacra.domaincore.utils.flow.timeout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first

interface DeviceDataBuilder {

    @ExperimentalCoroutinesApi
    suspend fun createDeviceData(): DeviceRequestModel

}

class DeviceDataBuilderImpl constructor(
    private val deviceManager: DeviceManager
) : DeviceDataBuilder {

    @ExperimentalCoroutinesApi
    override suspend fun createDeviceData(): DeviceRequestModel {
        val fcmToken = deviceManager.getFcmTokenFlow()
            .timeout(5000) // 5 Milliseconds
            .first()
        return DeviceRequestModel(
            deviceId = deviceManager.getAdvertisingId() ?: deviceManager.getHardwareId(),
            pushToken = fcmToken,
            timezone = deviceManager.getTimeZoneUTC()
        )
    }
}