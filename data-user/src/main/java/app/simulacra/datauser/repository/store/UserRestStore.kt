package app.simulacra.datauser.repository.store

import app.simulacra.datauser.rest.UserRestApi
import app.simulacra.datauser.rest.models.request.DeviceRequestModel
import app.simulacra.datauser.rest.models.request.LoginRequestModel
import app.simulacra.datauser.rest.models.request.RegisterUserRequestModel
import app.simulacra.datauser.rest.models.response.DeviceResponseModel
import app.simulacra.datauser.rest.models.response.LoginResponseModel
import app.simulacra.datauser.rest.models.response.UserResponseModel
import app.simulacra.networkcore.DataContainer
import app.simulacra.networkcore.dataContainerOf
import javax.inject.Inject

interface UserRestStore {

    fun createDevice(device: DeviceRequestModel): DataContainer<DeviceResponseModel>

    fun createUser(user: RegisterUserRequestModel): DataContainer<UserResponseModel>

    fun login(login: LoginRequestModel): DataContainer<LoginResponseModel>

}

class UserRestStoreImpl @Inject constructor(private val restApi: UserRestApi) : UserRestStore {

    override fun createDevice(device: DeviceRequestModel): DataContainer<DeviceResponseModel> {
        return dataContainerOf { restApi.registerDevice(device) }
    }

    override fun createUser(user: RegisterUserRequestModel): DataContainer<UserResponseModel> {
        return dataContainerOf { restApi.registerUser(user) }
    }

    override fun login(login: LoginRequestModel): DataContainer<LoginResponseModel> {
        return dataContainerOf { restApi.login(login) }
    }

}