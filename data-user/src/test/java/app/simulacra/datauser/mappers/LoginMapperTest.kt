package app.simulacra.datauser.mappers

import app.simulacra.datauser.datafactory.LoginDataFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LoginMapperTest {

    private val mapper: LoginMapper = LoginMapperImpl()

    @Test
    fun `map login data succeed`() {
        val deviceId = "testId"
        val loginRequest = LoginDataFactory.createLoginRequest()

        val result = mapper.mapLoginData(deviceId = deviceId, request = loginRequest)

        assertThat(result.deviceId).isEqualTo(deviceId)
        assertThat(result.email).isEqualTo(loginRequest.email)
        assertThat(result.password).isEqualTo(loginRequest.password)
        assertThat(result.isGuest).isEqualTo(loginRequest.isGuest)
    }
}