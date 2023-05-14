package app.simulacra.domainuser.datafactory

import app.simulacra.domainuser.models.Device

object DeviceDataFactory {

    fun createDevice(): Device {
        return Device(deviceId = "test_id", pushToken = "test_push_token", timeZone = 2100000L)
    }
}