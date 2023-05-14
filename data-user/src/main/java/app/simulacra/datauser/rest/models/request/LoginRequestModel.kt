package app.simulacra.datauser.rest.models.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestModel(
    @Json(name = "DeviceId") val deviceId: String,
    @Json(name = "Email") val email: String?,
    @Json(name = "Password") val password: String?,
    @Json(name = "IsGuest") val isGuest: Boolean = true
)