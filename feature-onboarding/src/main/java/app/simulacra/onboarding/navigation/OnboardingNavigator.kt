package app.simulacra.onboarding.navigation

import android.content.res.Resources
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import app.simulacra.featurecommon.utils.navigation.applyBottomToTopAnimations
import app.simulacra.featurecommon.utils.navigation.applyRightToLeftAnimations
import app.simulacra.featurecommon.utils.navigation.executeSafeNavAction
import app.simulacra.onboarding.R
import dagger.Lazy
import javax.inject.Inject

interface OnboardingNavigator {

    fun navigateToPolicy()

    fun navigateToMain()

    fun navigateToChooseTopic()
}

class OnboardingNavigatorImpl @Inject constructor(
    private val resources: Resources,
    private val navController: Lazy<NavController>
): OnboardingNavigator {


    override fun navigateToPolicy() {
        executeSafeNavAction {
            navController.get().navigate(resources.getString(R.string.policies_deep_link).toUri(),
                NavOptions.Builder().applyRightToLeftAnimations())
        }
    }

    override fun navigateToMain() {
        executeSafeNavAction {
            navController.get().navigate(resources.getString(R.string.discover_deep_link).toUri(),
                NavOptions.Builder().applyRightToLeftAnimations())
        }
    }

    override fun navigateToChooseTopic() {
        executeSafeNavAction {
            navController.get().navigate(R.id.action_show_choose_topics)
        }
    }
}