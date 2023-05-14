package app.simulacra.domainuser.models

data class Device(
    val deviceId: String,
    val pushToken: String,
    val timeZone: Long
)