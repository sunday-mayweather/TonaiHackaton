package app.simulacra.featurecommon.utils.notifications

enum class NotificationType(val id: Int) {
    PUSH(2707), READING(4357)
}

fun obtainIdByType(type: NotificationType): Int {
    return type.id
}