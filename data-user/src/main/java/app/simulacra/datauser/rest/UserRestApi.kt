package app.simulacra.datauser.rest

import app.simulacra.datauser.rest.models.request.DeviceRequestModel
import app.simulacra.datauser.rest.models.request.LoginRequestModel
import app.simulacra.datauser.rest.models.request.RegisterUserRequestModel
import app.simulacra.datauser.rest.models.response.DeviceResponseModel
import app.simulacra.datauser.rest.models.response.LoginResponseModel
import app.simulacra.datauser.rest.models.response.UserResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRestApi {

    @POST(value = "v1/auth/register_device")
    fun registerDevice(@Body request: DeviceRequestModel): Response<DeviceResponseModel>

    @POST(value = "v1/auth/register_user")
    fun registerUser(@Body request: RegisterUserRequestModel): Response<UserResponseModel>

    @POST(value = "v1/auth/login")
    fun login(@Body request: LoginRequestModel): Response<LoginResponseModel>

}