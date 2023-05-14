package app.simulacra.networkcore.token

import app.simulacra.domaincore.utils.device.DeviceManager
import app.simulacra.networkcore.token.storage.TokenStorage
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val AUTHORIZATION = "Authorization"
private const val API_BEARER_AUTHORIZATION = "Bearer %s"
private const val ACCEPT_LANGUAGE = "Accept-Language"
private const val USER_AGENT = "User-agent"
private const val USER_AGENT_FORMAT = "simulacra/%s Android/%s"

class HeaderInterceptor(
    private val deviceManager: DeviceManager,
    private val storage: TokenStorage
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .apply {
                addHeader(AUTHORIZATION, String.format(API_BEARER_AUTHORIZATION,
                    storage.getAccessToken()))
                addHeader(USER_AGENT, String.format(USER_AGENT_FORMAT, deviceManager.getAppVersionName(),
                    deviceManager.getOsVersionName()))
                addHeader(ACCEPT_LANGUAGE, deviceManager.getLocale())
            }.build()
        return chain.proceed(request)
    }
}