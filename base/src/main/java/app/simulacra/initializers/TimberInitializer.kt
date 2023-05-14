package app.simulacra.initializers

import android.app.Application
import app.simulacra.featurecommon.initializers.AppInitializer
import app.simulacra.featurecommon.utils.build.isBetaBuild
import app.simulacra.featurecommon.utils.build.isDevBuild
import app.simulacra.featurecommon.utils.build.isReleaseBuild
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor() : AppInitializer {

    override fun init(application: Application) {
        when {
            isDevBuild -> Timber.plant(Timber.DebugTree())
            isBetaBuild -> Timber.plant(Timber.DebugTree())
            isReleaseBuild -> { }
        }
    }
}