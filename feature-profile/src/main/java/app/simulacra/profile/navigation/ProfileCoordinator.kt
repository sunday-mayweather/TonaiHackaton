package app.simulacra.profile.navigation

import javax.inject.Inject

interface ProfileCoordinator {

    fun openSettings()

}

class ProfileCoordinatorImpl @Inject constructor(
    private val navigator: ProfileNavigator
): ProfileCoordinator {

    override fun openSettings() {
        navigator.navigateToSettings()
    }
}