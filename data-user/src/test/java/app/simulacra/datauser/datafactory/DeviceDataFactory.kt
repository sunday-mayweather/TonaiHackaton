package app.simulacra.datauser.datafactory

import app.simulacra.datauser.database.entities.DeviceEntity
import app.simulacra.datauser.rest.models.request.DeviceRequestModel
import app.simulacra.datauser.rest.models.response.DeviceResponseModel

@Suppress("unused")
object DeviceDataFactory {

    fun createDeviceEntity(): DeviceEntity {
        return DeviceEntity(id = 1, deviceId = "test_id", pushToken = "test_token", timeZone = 894L)
    }

    fun createDeviceResponseModel(): DeviceResponseModel {
        return DeviceResponseModel(deviceId = "test_id", pushToken = "test_id", timeZone = 2133450L)
    }

    fun createDeviceRequestModel(): DeviceRequestModel {
        return DeviceRequestModel(deviceId = "test_id", pushToken = "test_token", timezone = 28394L)
    }
}