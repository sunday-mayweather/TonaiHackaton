package app.simulacra.policies.injection

import app.simulacra.featurecommon.injection.scopes.FragmentScope
import app.simulacra.policies.navigation.PoliciesCoordinator
import app.simulacra.policies.navigation.PoliciesCoordinatorImpl
import app.simulacra.policies.navigation.PoliciesNavigator
import app.simulacra.policies.navigation.PoliciesNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class PoliciesModule {

    @Binds
    @FragmentScope
    abstract fun bindNavigator(navigator: PoliciesNavigatorImpl): PoliciesNavigator

    @Binds
    abstract fun bindCoordinator(coordinatorImpl: PoliciesCoordinatorImpl): PoliciesCoordinator

}