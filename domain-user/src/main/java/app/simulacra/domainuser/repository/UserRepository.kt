package app.simulacra.domainuser.repository

import app.simulacra.domainuser.interactors.LoginRequest
import app.simulacra.domainuser.interactors.UpdateUserRequest
import app.simulacra.domainuser.models.Device
import app.simulacra.domainuser.models.User

interface UserRepository {

    suspend fun getDevice(): Device

    suspend fun getUser():User

    suspend fun saveUser(request: UpdateUserRequest)

    suspend fun login(request: LoginRequest)

}