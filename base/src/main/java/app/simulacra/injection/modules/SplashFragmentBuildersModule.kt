package app.simulacra.injection.modules

import app.simulacra.featurecommon.injection.scopes.FragmentScope
import app.simulacra.sections.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashFragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun createSplashFragmentInjector(): SplashFragment

}