package app.simulacra.navigation

import javax.inject.Inject

interface HomeCoordinator {

    fun start(isFirstLaunch: Boolean)

}

class HomeCoordinatorImpl @Inject constructor(
    private val navigator: HomeNavigator
) : HomeCoordinator {

    override fun start(isFirstLaunch: Boolean) {
        when (isFirstLaunch) {
            false -> navigator.navigateToMain()
            true -> navigator.navigateToOnboarding()
        }
    }
}