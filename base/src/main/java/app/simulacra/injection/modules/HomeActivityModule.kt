package app.simulacra.injection.modules

import android.content.Context
import app.simulacra.activequest.injection.ActiveQuestFragmentBuildersModule
import app.simulacra.discover.injection.DiscoverFragmentBuildersModule
import app.simulacra.featurecommon.injection.scopes.ActivityScope
import app.simulacra.onboarding.injection.OnboardingFragmentBuildersModule
import app.simulacra.policies.injection.PoliciesFragmentBuildersModule
import app.simulacra.profile.injection.ProfileFragmentBuildersModule
import app.simulacra.sections.home.HomeActivity
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityModule {

    companion object {

        @Provides
        fun provideAppUpdateManager(context: Context): AppUpdateManager =
            AppUpdateManagerFactory.create(context)
    }

    @ActivityScope
    @ContributesAndroidInjector(modules = [
        SplashFragmentBuildersModule::class,
        MainNavigationModule::class,
        OnboardingFragmentBuildersModule::class,
        DiscoverFragmentBuildersModule::class,
        ActiveQuestFragmentBuildersModule::class,
        ProfileFragmentBuildersModule::class,
        PoliciesFragmentBuildersModule::class
    ])
    abstract fun createHomeActivityInjector(): HomeActivity

}