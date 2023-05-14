package app.simulacra.networkcore.token

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthTokenModel(
    @Json(name = "AccessToken") val accessToken: String,
    @Json(name = "RefreshToken") val refreshToken: String
)