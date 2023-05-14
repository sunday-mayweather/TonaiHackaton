package app.simulacra.policies.injection

import app.simulacra.featurecommon.injection.scopes.FragmentScope
import app.simulacra.policies.sections.PolicyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PoliciesFragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [PoliciesModule::class])
    abstract fun createPolciyFragmentInjector(): PolicyFragment

}