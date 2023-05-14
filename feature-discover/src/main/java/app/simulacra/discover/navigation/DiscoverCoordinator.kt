package app.simulacra.discover.navigation

import javax.inject.Inject

interface DiscoverCoordinator {

    fun openSummaryDetails(sourceUrl: String)

}

class DiscoverCoordinatorImpl @Inject constructor(
    private val navigator: DiscoverNavigator
): DiscoverCoordinator {

    override fun openSummaryDetails(sourceUrl: String) {
        navigator.navigateToSummaryDetails(sourceUrl)
    }

}