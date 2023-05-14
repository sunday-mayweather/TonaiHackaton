package app.simulacra.featurecommon.utils.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import app.simulacra.domaincore.utils.preferences.Preferences
import app.simulacra.domaincore.utils.preferences.Preferences.Companion.KEY_AUDIO_ENABLED
import app.simulacra.domaincore.utils.preferences.Preferences.Companion.KEY_CHOSEN_TOPICS
import app.simulacra.domaincore.utils.preferences.Preferences.Companion.KEY_FIRST_LAUNCH
import javax.inject.Inject

class PreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Preferences {

    override fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    override fun markAlreadyLaunched() {
        sharedPreferences.edit { putBoolean(KEY_FIRST_LAUNCH, false) }
    }

    override fun isAudioEnabled(): Boolean {
        return sharedPreferences.getBoolean(KEY_AUDIO_ENABLED, false)
    }

    override fun updateAudioEnabled(isEnabled: Boolean) {
        sharedPreferences.edit { putBoolean(KEY_AUDIO_ENABLED, isEnabled) }
    }

    override fun saveTopics(topics: Set<String>) {
        sharedPreferences.edit { putStringSet(KEY_CHOSEN_TOPICS, topics) }
    }

    override fun getTopics(): Set<String> {
        return sharedPreferences.getStringSet(KEY_CHOSEN_TOPICS, emptySet()).orEmpty()
    }

}