package app.simulacra

import android.app.Application
import app.simulacra.featurecommon.initializers.AppInitializers
import app.simulacra.featurecommon.injection.utils.AppInjector
import app.simulacra.injection.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SimulacraApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var appInitializers: AppInitializers

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this) {
            DaggerAppComponent.builder().application(this).appContext(this)
                .build().inject(this)
        }

        appInitializers.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}