package app.simulacra.discover.injection

import app.simulacra.discover.sections.DiscoverFragment
import app.simulacra.discover.sections.details.StoryDetailsFragment
import app.simulacra.featurecommon.injection.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DiscoverFragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [DiscoverModule::class])
    abstract fun createDiscoverFragmentInjector(): DiscoverFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DiscoverModule::class])
    abstract fun createStoryDetailsFragmentInjector(): StoryDetailsFragment
}