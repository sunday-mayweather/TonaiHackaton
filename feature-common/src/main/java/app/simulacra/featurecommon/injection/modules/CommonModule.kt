package app.simulacra.featurecommon.injection.modules

import app.simulacra.domaincore.utils.connectivity.ConnectivityManager
import app.simulacra.domaincore.utils.device.DeviceManager
import app.simulacra.featurecommon.utils.connectivity.ConnectivityManagerImpl
import app.simulacra.featurecommon.utils.device.DeviceManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [PreferencesModule::class])
abstract class CommonModule {

    @Binds
    abstract fun bindsConnectivityManager(connectivityManagerImpl: ConnectivityManagerImpl): ConnectivityManager

    @Binds
    @Singleton
    abstract fun bindsDeviceManager(deviceManagerImpl: DeviceManagerImpl): DeviceManager
}