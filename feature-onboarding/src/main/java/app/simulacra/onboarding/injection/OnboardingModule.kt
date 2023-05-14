package app.simulacra.onboarding.injection

import app.simulacra.featurecommon.injection.scopes.FragmentScope
import app.simulacra.onboarding.navigation.OnboardingCoordinator
import app.simulacra.onboarding.navigation.OnboardingCoordinatorImpl
import app.simulacra.onboarding.navigation.OnboardingNavigator
import app.simulacra.onboarding.navigation.OnboardingNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class OnboardingModule {

    @Binds
    @FragmentScope
    abstract fun bindNavigator(navigator: OnboardingNavigatorImpl): OnboardingNavigator

    @Binds
    abstract fun bindCoordinator(coordinatorImpl: OnboardingCoordinatorImpl): OnboardingCoordinator
}