package app.simulacra.datadiscover.injection

import android.content.Context
import app.simulacra.datadiscover.data.StoriesJsonStore
import app.simulacra.datadiscover.data.StoriesJsonStoreImpl
import app.simulacra.datadiscover.repository.DiscoverRepositoryImpl
import app.simulacra.domaindiscover.DiscoverRepository
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DiscoverDataModule {

    companion object {

        @Provides
        @Singleton
        fun provideStoriesJsonStore(moshi: Moshi, context: Context): StoriesJsonStore {
            return StoriesJsonStoreImpl(moshi, context)
        }
    }

    @Binds
    abstract fun bindStoriesRepository(discoverRepository: DiscoverRepositoryImpl): DiscoverRepository

}