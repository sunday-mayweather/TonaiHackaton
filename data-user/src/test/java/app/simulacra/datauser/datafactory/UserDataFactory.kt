package app.simulacra.datauser.datafactory

import app.simulacra.datauser.database.entities.UserEntity
import app.simulacra.datauser.rest.models.request.RegisterUserRequestModel
import app.simulacra.datauser.rest.models.response.UserResponseModel
import app.simulacra.domainuser.models.User

@Suppress("unused")
object UserDataFactory {

    fun createUser(): User {
        return User(id = 1, nickName = "test_nickname", email = "test@mail.com")
    }

    fun createUserEntity(): UserEntity {
        return UserEntity(userId = 1, nickName = "test_nickname", email = "test@mail.com")
    }

    fun createUserResponseModel(): UserResponseModel {
        return UserResponseModel(deviceId = "test_id", nickName = "test_nickname", email = null, userId = 1)
    }

    fun createUserRequestModel(): RegisterUserRequestModel {
        return RegisterUserRequestModel(deviceId = "test_id", nickName = "test", email = null, password = null)
    }
}