package app.simulacra.injection.components

import android.app.Application
import android.content.Context
import app.simulacra.SimulacraApp
import app.simulacra.datadiscover.injection.DiscoverDataModule
import app.simulacra.datauser.injection.UserDataModule
import app.simulacra.featurecommon.injection.modules.CommonModule
import app.simulacra.injection.modules.AppModule
import app.simulacra.injection.modules.HomeActivityModule
import app.simulacra.networkcore.injection.NetworkCoreModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        HomeActivityModule::class,
        CommonModule::class,
        NetworkCoreModule::class,
        UserDataModule::class,
        DiscoverDataModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: SimulacraApp)
}