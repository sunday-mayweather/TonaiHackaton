package app.simulacra.profile.injection

import app.simulacra.featurecommon.injection.scopes.FragmentScope
import app.simulacra.profile.navigation.ProfileCoordinator
import app.simulacra.profile.navigation.ProfileCoordinatorImpl
import app.simulacra.profile.navigation.ProfileNavigator
import app.simulacra.profile.navigation.ProfileNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ProfileModule {

    @Binds
    @FragmentScope
    abstract fun bindNavigator(navigator: ProfileNavigatorImpl): ProfileNavigator

    @Binds
    abstract fun bindCoordinator(coordinatorImpl: ProfileCoordinatorImpl): ProfileCoordinator
}