package app.simulacra.networkcore.injection

import android.content.Context
import app.simulacra.domaincore.utils.device.DeviceManager
import app.simulacra.networkcore.BuildConfig
import app.simulacra.networkcore.token.HeaderInterceptor
import app.simulacra.networkcore.token.TokenAuthenticator
import app.simulacra.networkcore.token.TokenRestApi
import app.simulacra.networkcore.token.storage.TokenStorage
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkCoreModule {

    private const val CACHE_SIZE = 10L * 1024L * 1024L
    private const val CACHE_DIR_NAME = "simulacraResponseCache"

    private const val CLIENT_CONNECT_TIMEOUT_SECONDS = 30L
    private const val CLIENT_READ_TIMEOUT_SECONDS = 30L
    private const val CLIENT_WRITE_TIMEOUT_SECONDS = 10L

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        return Cache(File(context.cacheDir, CACHE_DIR_NAME), CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideNetworkCoreStorage(context: Context) = TokenStorage(context)

    @Provides
    @Singleton
    fun provideChuckerInterceptor(context: Context) = ChuckerInterceptor.Builder(context)
        .collector(ChuckerCollector(context))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(false)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
        moshi: Moshi,
        chuckInterceptor: ChuckerInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenStorage: TokenStorage,
        deviceManager: DeviceManager
    ): OkHttpClient {

        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.addNetworkInterceptor(HeaderInterceptor(deviceManager, tokenStorage))
        if (BuildConfig.DEBUG) {
            clientBuilder
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(chuckInterceptor)
        }
        clientBuilder
            .cache(cache)
            .readTimeout(CLIENT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(CLIENT_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)

        clientBuilder.authenticator(TokenAuthenticator(provideTokenApiService(clientBuilder.build(),
            moshi), tokenStorage)
        )

        return clientBuilder.build()
    }

    private fun provideTokenApiService(okHttpClient: OkHttpClient, moshi: Moshi): TokenRestApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(TokenRestApi::class.java)
    }
}