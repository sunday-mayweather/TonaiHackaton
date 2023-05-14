package app.simulacra.discover.injection

import app.simulacra.discover.navigation.DiscoverCoordinator
import app.simulacra.discover.navigation.DiscoverCoordinatorImpl
import app.simulacra.discover.navigation.DiscoverNavigator
import app.simulacra.discover.navigation.DiscoverNavigatorImpl
import app.simulacra.featurecommon.injection.scopes.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class DiscoverModule {

    @Binds
    @FragmentScope
    abstract fun bindNavigator(navigator: DiscoverNavigatorImpl): DiscoverNavigator

    @Binds
    abstract fun bindCoordinator(coordinatorImpl: DiscoverCoordinatorImpl): DiscoverCoordinator

}