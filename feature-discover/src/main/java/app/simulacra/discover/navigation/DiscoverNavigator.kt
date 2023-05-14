package app.simulacra.discover.navigation

import android.content.res.Resources
import androidx.navigation.NavController
import app.simulacra.discover.sections.DiscoverFragmentDirections
import app.simulacra.featurecommon.utils.navigation.executeSafeNavAction
import dagger.Lazy
import javax.inject.Inject

interface DiscoverNavigator {

    fun navigateToSummaryDetails(sourceUrl: String)

}

class DiscoverNavigatorImpl @Inject constructor(
    private val navController: Lazy<NavController>,
    private val resources: Resources
): DiscoverNavigator {

    override fun navigateToSummaryDetails(sourceUrl: String) {
        executeSafeNavAction {
            navController.get().navigate(DiscoverFragmentDirections.actionShowDetails(sourceUrl = sourceUrl))
        }
    }

}