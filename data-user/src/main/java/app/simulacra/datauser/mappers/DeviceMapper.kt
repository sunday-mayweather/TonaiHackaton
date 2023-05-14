package app.simulacra.datauser.mappers

import app.simulacra.datauser.database.entities.DeviceEntity
import app.simulacra.datauser.rest.models.response.DeviceResponseModel
import app.simulacra.domainuser.models.Device

interface DeviceMapper {

    fun mapToLocal(response: DeviceResponseModel): DeviceEntity

    fun mapToDomain(entity: DeviceEntity): Device
}

class DeviceMapperImpl : DeviceMapper {

    override fun mapToLocal(response: DeviceResponseModel): DeviceEntity {
        return DeviceEntity(
            deviceId = response.deviceId, pushToken = response.pushToken,
            timeZone = response.timeZone
        )
    }

    override fun mapToDomain(entity: DeviceEntity): Device {
        return Device(
            deviceId = entity.deviceId,
            pushToken = entity.pushToken,
            timeZone = entity.timeZone
        )
    }
}