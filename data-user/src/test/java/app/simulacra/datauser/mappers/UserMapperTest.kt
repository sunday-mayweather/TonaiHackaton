package app.simulacra.datauser.mappers

import app.simulacra.datauser.datafactory.UserDataFactory
import app.simulacra.domainuser.interactors.UpdateUserRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserMapperTest {

    private val mapper: UserMapper = UserMapperImpl()

    @Test
    fun `map to domain succeed`() {
        val userEntity= UserDataFactory.createUserEntity()

        val result = mapper.mapToDomain(userEntity)

        assertThat(result.id).isEqualTo(userEntity.userId)
        assertThat(result.nickName).isEqualTo(userEntity.nickName)
        assertThat(result.email).isEqualTo(userEntity.email)
    }

    @Test
    fun `map to local succeed when some values are null`() {
        val userResponse = UserDataFactory.createUserResponseModel()
            .copy(nickName = null, email = null)

        val result = mapper.mapToLocal(userResponse)

        assertThat(result.userId).isEqualTo(userResponse.userId)
        assertThat(result.nickName).isEmpty()
        assertThat(result.email).isEmpty()
    }

    @Test
    fun `map to local succeed when all values are NOT null`() {
        val userResponse = UserDataFactory.createUserResponseModel()
            .copy(nickName = "test", email = "test@mail.com")

        val result = mapper.mapToLocal(userResponse)

        assertThat(result.userId).isEqualTo(userResponse.userId)
        assertThat(result.nickName).isEqualTo(userResponse.nickName)
        assertThat(result.email).isEqualTo(userResponse.email)
    }

    @Test
    fun `map to remote succeed`() {
        val deviceId = "TestId"
        val updateRequest = UpdateUserRequest(nickName = "test", email = null, password = null)

        val result = mapper.mapToRemote(deviceId, updateRequest)

        assertThat(result.deviceId).isEqualTo(deviceId)
        assertThat(result.nickName).isEqualTo(updateRequest.nickName)
        assertThat(result.email).isEqualTo(updateRequest.email)
        assertThat(result.password).isEqualTo(updateRequest.password)
    }
}