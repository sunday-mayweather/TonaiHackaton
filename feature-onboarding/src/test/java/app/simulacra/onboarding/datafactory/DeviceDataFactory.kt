package app.simulacra.onboarding.datafactory

import app.simulacra.domainuser.models.Device

object DeviceDataFactory {

    fun createDevice(): Device {
        return Device(deviceId = "TestId", pushToken = "TestToken", timeZone = 112304L)
    }
}