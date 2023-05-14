package app.simulacra.datauser.injection

import android.content.Context
import androidx.room.Room
import app.simulacra.datauser.database.UserDatabase
import app.simulacra.datauser.mappers.*
import app.simulacra.datauser.repository.DeviceDataBuilder
import app.simulacra.datauser.repository.DeviceDataBuilderImpl
import app.simulacra.datauser.repository.UserRepositoryImpl
import app.simulacra.datauser.repository.store.UserLocalStore
import app.simulacra.datauser.repository.store.UserLocalStoreImpl
import app.simulacra.datauser.repository.store.UserRestStore
import app.simulacra.datauser.repository.store.UserRestStoreImpl
import app.simulacra.datauser.rest.UserRestApi
import app.simulacra.domaincore.utils.device.DeviceManager
import app.simulacra.domainuser.repository.UserRepository
import app.simulacra.networkcore.token.storage.TokenStorage
import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object UserDataModule {

    private const val USER_DATABASE_NAME = "user_database.db"

    @Provides
    @Singleton
    @JvmStatic
    fun provideApiService(okHttpClient: Lazy<OkHttpClient>, moshi: Moshi): UserRestApi {
        return Retrofit.Builder()
            .baseUrl("https://google.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .callFactory(okHttpClient.get())
            .build()
            .create(UserRestApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideDatabase(context: Context): UserDatabase = Room.databaseBuilder(
        context.applicationContext, UserDatabase::class.java, USER_DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUserRestStore(restApi: UserRestApi): UserRestStore {
        return UserRestStoreImpl(restApi)
    }

    @Provides
    fun providesUserLocalStore(database: UserDatabase): UserLocalStore {
        return UserLocalStoreImpl(database)
    }

    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper = UserMapperImpl()

    @Provides
    @Singleton
    fun provideLoginMapper(): LoginMapper = LoginMapperImpl()

    @Provides
    @Singleton
    fun provideDeviceMapper(): DeviceMapper = DeviceMapperImpl()

    @Provides
    @Singleton
    fun provideDeviceDataBuilder(device: DeviceManager): DeviceDataBuilder =
        DeviceDataBuilderImpl(device)

    @Provides
    @Singleton
    fun provideUserRepository(
        restStore: UserRestStore, localStore: UserLocalStore,
        tokenStorage: TokenStorage, userMapper: UserMapper,
        loginMapper: LoginMapper, deviceMapper: DeviceMapper,
        deviceDataBuilder: DeviceDataBuilder
    ): UserRepository {
        return UserRepositoryImpl(
            restStore, localStore, tokenStorage, userMapper, loginMapper,
            deviceMapper, deviceDataBuilder
        )
    }
}