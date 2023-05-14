package app.simulacra.policies.navigation

import javax.inject.Inject

interface PoliciesCoordinator {

    fun backPressed()

}

class PoliciesCoordinatorImpl @Inject constructor(
    private val navigator: PoliciesNavigator
): PoliciesCoordinator {

    override fun backPressed() {
        navigator.navigateBack()
    }
}