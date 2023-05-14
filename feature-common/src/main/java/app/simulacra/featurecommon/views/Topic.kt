package app.simulacra.featurecommon.views

enum class Topic {
    Micronutrients,
    Longevity,
    Cognitive,
    Muscle,
    Sleep
}

fun Topic.asString(): String {
    return when(this) {
        Topic.Micronutrients -> "Micronutrients"
        Topic.Longevity -> "Longevity"
        Topic.Cognitive -> "Cognitive"
        Topic.Muscle -> "Muscle"
        Topic.Sleep -> "Sleep"
    }
}

fun String.toTopic(): Topic {
    return when(this) {
        "Micronutrients" -> Topic.Micronutrients
        "Longevity" -> Topic.Longevity
        "Cognitive" -> Topic.Cognitive
        "Muscle" -> Topic.Muscle
        "Sleep" -> Topic.Sleep
        else -> throw IllegalArgumentException("Can't convert string $this to the topic!")
    }
}