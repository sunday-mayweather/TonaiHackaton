package app.simulacra.datauser.rest.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponseModel(
    @Json(name = "DeviceId") val deviceId: String,
    @Json(name = "Nickname") val nickName: String?,
    @Json(name = "Email") val email: String?,
    @Json(name = "UserId") val userId: Int
)