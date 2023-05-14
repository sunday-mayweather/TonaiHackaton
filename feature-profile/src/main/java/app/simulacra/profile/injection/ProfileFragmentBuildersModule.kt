package app.simulacra.profile.injection

import app.simulacra.featurecommon.injection.scopes.FragmentScope
import app.simulacra.profile.sections.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProfileFragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ProfileModule::class])
    abstract fun createProfileFragmentInjector(): ProfileFragment

}