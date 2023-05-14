package app.simulacra.navigation

import androidx.navigation.NavController
import app.simulacra.R
import app.simulacra.featurecommon.utils.navigation.executeSafeNavAction
import dagger.Lazy
import javax.inject.Inject

interface HomeNavigator {

    fun navigateToOnboarding()

    fun navigateToMain()

}

class HomeNavigatorImpl @Inject constructor(
    private val navController: Lazy<NavController>
) : HomeNavigator {

    override fun navigateToOnboarding() {
        executeSafeNavAction {
            navController.get().navigate(R.id.action_show_onboarding)
        }
    }

    override fun navigateToMain() {
        executeSafeNavAction {
            navController.get().navigate(R.id.action_show_discover)
        }
    }
}