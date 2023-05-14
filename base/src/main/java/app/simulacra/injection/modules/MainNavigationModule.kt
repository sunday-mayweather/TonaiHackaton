package app.simulacra.injection.modules

import androidx.navigation.NavController
import androidx.navigation.Navigation
import app.simulacra.R
import app.simulacra.featurecommon.injection.scopes.ActivityScope
import app.simulacra.navigation.HomeCoordinator
import app.simulacra.navigation.HomeCoordinatorImpl
import app.simulacra.navigation.HomeNavigator
import app.simulacra.navigation.HomeNavigatorImpl
import app.simulacra.sections.home.HomeActivity
import dagger.Module
import dagger.Provides

@Module
class MainNavigationModule {

    @Provides
    @ActivityScope
    fun provideNavController(activity: HomeActivity): NavController =
        Navigation.findNavController(activity, R.id.mainNavigationFragment)

    @Provides
    fun bindNavigator(navigatorImpl: HomeNavigatorImpl): HomeNavigator {
        return navigatorImpl
    }

    @Provides
    fun bindCoordinator(coordinatorImpl: HomeCoordinatorImpl): HomeCoordinator {
        return coordinatorImpl
    }
}