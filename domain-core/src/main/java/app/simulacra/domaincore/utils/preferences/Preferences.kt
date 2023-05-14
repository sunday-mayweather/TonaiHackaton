package app.simulacra.domaincore.utils.preferences

interface Preferences {

    companion object {
        const val KEY_FIRST_LAUNCH = "first_launch"
        const val KEY_AUDIO_ENABLED = "audio_enabled"
        const val KEY_CHOSEN_TOPICS = "chosen_topics"
    }

    fun isFirstLaunch(): Boolean

    fun markAlreadyLaunched()

    fun isAudioEnabled(): Boolean

    fun updateAudioEnabled(isEnabled: Boolean)

    fun saveTopics(topics: Set<String>)

    fun getTopics(): Set<String>

}