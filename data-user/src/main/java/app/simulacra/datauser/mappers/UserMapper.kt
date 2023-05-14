package app.simulacra.datauser.mappers

import app.simulacra.datauser.database.entities.UserEntity
import app.simulacra.datauser.rest.models.request.RegisterUserRequestModel
import app.simulacra.datauser.rest.models.response.UserResponseModel
import app.simulacra.domainuser.interactors.UpdateUserRequest
import app.simulacra.domainuser.models.User

interface UserMapper {

    fun mapToDomain(entity: UserEntity): User

    fun mapToLocal(response: UserResponseModel): UserEntity

    fun mapToRemote(deviceId: String, request: UpdateUserRequest): RegisterUserRequestModel

}

class UserMapperImpl : UserMapper {

    override fun mapToDomain(entity: UserEntity): User {
        return User(id = entity.userId, nickName = entity.nickName, email = entity.email)
    }

    override fun mapToLocal(response: UserResponseModel): UserEntity {
        return UserEntity(
            userId = response.userId,
            nickName = response.nickName ?: "",
            email = response.email ?: ""
        )
    }

    override fun mapToRemote(
        deviceId: String,
        request: UpdateUserRequest
    ): RegisterUserRequestModel {
        return RegisterUserRequestModel(
            deviceId = deviceId, nickName = request.nickName,
            email = request.email, password = request.password
        )
    }

}