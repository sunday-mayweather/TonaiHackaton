package app.simulacra.activequest.injection

import app.simulacra.activequest.sections.ActiveQuestFragment
import app.simulacra.featurecommon.injection.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActiveQuestFragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ActiveQuestModule::class])
    abstract fun createActiveQuestFragmentInjector(): ActiveQuestFragment

}