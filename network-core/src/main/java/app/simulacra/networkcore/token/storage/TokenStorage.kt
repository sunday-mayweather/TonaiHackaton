package app.simulacra.networkcore.token.storage

import android.content.Context
import androidx.core.content.edit
import javax.inject.Inject

class TokenStorage @Inject constructor(context: Context) {

    companion object {
        private const val ACCESS_TOKEN_KEY = "at_key"
        private const val REFRESH_TOKEN_KEY = "rt_key"
        private const val PREF_KEY = "prefs_network_core"
    }

    private val preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)

    fun getAccessToken(): String {
        return Encryptor.decrypt(preferences.getString(ACCESS_TOKEN_KEY, "")!!)
    }

    fun getRefreshToken(): String {
        return Encryptor.decrypt(preferences.getString(REFRESH_TOKEN_KEY, "")!!)
    }

    fun setAccessToken(value: String) {
        preferences.edit{ putString(ACCESS_TOKEN_KEY, Encryptor.encrypt(value)) }
    }

    fun setRefreshToken(value: String) {
        preferences.edit{ putString(REFRESH_TOKEN_KEY, Encryptor.encrypt(value)) }
    }
}