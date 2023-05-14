package app.simulacra.datauser.mappers

import app.simulacra.datauser.rest.models.request.LoginRequestModel
import app.simulacra.domainuser.interactors.LoginRequest

interface LoginMapper {

    fun mapLoginData(deviceId: String, request: LoginRequest): LoginRequestModel

}

class LoginMapperImpl : LoginMapper {

    override fun mapLoginData(deviceId: String, request: LoginRequest): LoginRequestModel {
        return LoginRequestModel(
            deviceId = deviceId, email = request.email, password = request.password,
            isGuest = !(request.email != null && request.password !== null)
        )
    }

}