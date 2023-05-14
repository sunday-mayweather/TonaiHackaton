package app.simulacra.onboarding.injection

import app.simulacra.featurecommon.injection.scopes.FragmentScope
import app.simulacra.onboarding.sections.ChooseTopicsFragment
import app.simulacra.onboarding.sections.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OnboardingFragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [OnboardingModule::class])
    abstract fun createWelcomeFragmentInjector(): WelcomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [OnboardingModule::class])
    abstract fun createChooseTopicFragmentInjector(): ChooseTopicsFragment
}