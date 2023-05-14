package app.simulacra.policies.navigation

import androidx.navigation.NavController
import app.simulacra.featurecommon.utils.navigation.executeSafeNavAction
import dagger.Lazy
import javax.inject.Inject

interface PoliciesNavigator {

    fun navigateBack()

}

class PoliciesNavigatorImpl @Inject constructor(
    private val navController: Lazy<NavController>
): PoliciesNavigator {

    override fun navigateBack() {
        executeSafeNavAction {
            navController.get().navigateUp()
        }
    }

}