package app.simulacra.datauser.rest.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseModel(
    @Json(name = "AccessToken") val accessToken: String,
    @Json(name = "RefreshToken") val refreshToken: String
)