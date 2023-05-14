package app.simulacra.datauser.mappers

import app.simulacra.datauser.datafactory.DeviceDataFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeviceMapperTest {

    private val mapper: DeviceMapper = DeviceMapperImpl()

    @Test
    fun `map to local succeed`() {
        val deviceResponse = DeviceDataFactory.createDeviceResponseModel()

        val result = mapper.mapToLocal(deviceResponse)

        assertThat(result.deviceId).isEqualTo(deviceResponse.deviceId)
        assertThat(result.pushToken).isEqualTo(deviceResponse.pushToken)
        assertThat(result.timeZone).isEqualTo(deviceResponse.timeZone)
    }

    @Test
    fun `map to domain succeed`() {
        val deviceEntity = DeviceDataFactory.createDeviceEntity()

        val result = mapper.mapToDomain(deviceEntity)

        assertThat(result.deviceId).isEqualTo(deviceEntity.deviceId)
        assertThat(result.pushToken).isEqualTo(deviceEntity.pushToken)
        assertThat(result.timeZone).isEqualTo(result.timeZone)
    }
}