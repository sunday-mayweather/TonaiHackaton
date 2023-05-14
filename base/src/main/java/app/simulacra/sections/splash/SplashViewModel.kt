package app.simulacra.sections.splash

import androidx.lifecycle.ViewModel
import app.simulacra.domaincore.utils.preferences.Preferences
import app.simulacra.navigation.HomeCoordinator
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val preferences: Preferences,
    private val coordinator: HomeCoordinator
) : ViewModel() {

    fun handleAppLaunch() {
        coordinator.start(isFirstLaunch = preferences.isFirstLaunch())
        preferences.markAlreadyLaunched()
    }
}