package app.simulacra.injection.modules

import android.content.Context
import android.content.res.Resources
import app.simulacra.featurecommon.initializers.AppInitializers
import app.simulacra.initializers.AppLifecycleObserversInitializer
import app.simulacra.initializers.FirebaseInitializer
import app.simulacra.initializers.TimberInitializer
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {

    companion object {

        @Provides
        fun provideAppInitializers(
            timberInitializer: TimberInitializer,
            firebaseInitializer: FirebaseInitializer,
            appLifecycleObserversInitializer: AppLifecycleObserversInitializer
        ) = AppInitializers(
            timberInitializer, firebaseInitializer, appLifecycleObserversInitializer
        )

        @Provides
        fun provideResources(context: Context): Resources = context.resources

    }
}