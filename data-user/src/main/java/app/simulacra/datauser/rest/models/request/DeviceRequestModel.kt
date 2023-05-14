package app.simulacra.datauser.rest.models.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeviceRequestModel(
    @Json(name = "DeviceId") val deviceId: String,
    @Json(name = "PushToken") val pushToken: String,
    @Json(name = "Timezone") val timezone: Long
)