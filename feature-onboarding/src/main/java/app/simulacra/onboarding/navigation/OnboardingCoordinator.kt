package app.simulacra.onboarding.navigation

import javax.inject.Inject

interface OnboardingCoordinator {

    fun openPolicy()

    fun openMain()

    fun openChooseTopic()
}

class OnboardingCoordinatorImpl @Inject constructor(
    private val navigator: OnboardingNavigator
): OnboardingCoordinator {

    override fun openPolicy() {
        navigator.navigateToPolicy()
    }

    override fun openMain() {
        navigator.navigateToMain()
    }

    override fun openChooseTopic() {
        navigator.navigateToChooseTopic()
    }
}