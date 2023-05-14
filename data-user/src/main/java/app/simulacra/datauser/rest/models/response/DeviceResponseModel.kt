package app.simulacra.datauser.rest.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeviceResponseModel(
    @Json(name = "DeviceId") val deviceId: String,
    @Json(name = "PushToken") val pushToken: String,
    @Json(name = "Timezone") val timeZone: Long
)