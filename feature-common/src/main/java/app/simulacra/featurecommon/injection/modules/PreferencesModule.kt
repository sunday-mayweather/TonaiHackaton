package app.simulacra.featurecommon.injection.modules

import android.content.Context
import android.content.SharedPreferences
import app.simulacra.domaincore.utils.preferences.Preferences
import app.simulacra.featurecommon.R
import app.simulacra.featurecommon.utils.preference.PreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class PreferencesModule {

    @Module
    companion object {
        private const val PREFERENCE = "Preference"

        @Provides
        @JvmStatic
        @Singleton
        fun provideSharedPreferencesInstance(context: Context): SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name) + PREFERENCE,
                Context.MODE_PRIVATE)
    }

    @Binds
    @Singleton
    abstract fun bindPreferences(preferencesImpl: PreferencesImpl): Preferences
}