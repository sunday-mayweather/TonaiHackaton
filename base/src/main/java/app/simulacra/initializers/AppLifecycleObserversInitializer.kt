package app.simulacra.initializers

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import app.simulacra.featurecommon.initializers.AppInitializer
import javax.inject.Inject

class AppLifecycleObserversInitializer @Inject constructor() : AppInitializer {

    override fun init(application: Application) {
        val lifecycle = ProcessLifecycleOwner.get().lifecycle

        lifecycle.run {
            // Do stuff
        }
    }
}