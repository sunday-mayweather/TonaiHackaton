package app.simulacra.datauser.respository

import app.simulacra.datauser.repository.DeviceDataBuilder
import app.simulacra.datauser.repository.DeviceDataBuilderImpl
import app.simulacra.domaincore.utils.device.DeviceManager
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class DeviceDataBuilderTest {

    private val device: DeviceManager = mock()

    private val deviceDataBuilder: DeviceDataBuilder = DeviceDataBuilderImpl(device)


}