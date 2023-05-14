package app.simulacra.datauser.repository

import app.simulacra.datauser.mappers.DeviceMapper
import app.simulacra.datauser.mappers.LoginMapper
import app.simulacra.datauser.mappers.UserMapper
import app.simulacra.datauser.repository.store.UserLocalStore
import app.simulacra.datauser.repository.store.UserRestStore
import app.simulacra.domainuser.interactors.LoginRequest
import app.simulacra.domainuser.interactors.UpdateUserRequest
import app.simulacra.domainuser.models.Device
import app.simulacra.domainuser.models.User
import app.simulacra.domainuser.repository.UserRepository
import app.simulacra.networkcore.token.storage.TokenStorage
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val restStore: UserRestStore,
    private val localStore: UserLocalStore,
    private val tokenStorage: TokenStorage,
    private val userMapper: UserMapper,
    private val loginMapper: LoginMapper,
    private val deviceMapper: DeviceMapper,
    private val deviceDataBuilder: DeviceDataBuilder
) : UserRepository {

    override suspend fun getDevice(): Device {
        TODO("think if this one is still needed")
    }

    override suspend fun getUser(): User {
        TODO("think if this one is still needed")
    }

    override suspend fun saveUser(request: UpdateUserRequest) {

    }

    override suspend fun login(request: LoginRequest) {

    }

}