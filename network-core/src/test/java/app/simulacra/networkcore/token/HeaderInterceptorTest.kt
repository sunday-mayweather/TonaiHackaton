package app.simulacra.networkcore.token

import app.simulacra.domaincore.utils.device.DeviceManager
import app.simulacra.networkcore.datafactory.AuthTokenDataFactory
import app.simulacra.networkcore.token.storage.TokenStorage
import com.nhaarman.mockitokotlin2.*
import okhttp3.Interceptor
import okhttp3.Request
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class HeaderInterceptorTest {

    private val storage: TokenStorage = mock()
    private val deviceManager: DeviceManager = mock()

    private val request: Request = mock {
        on { newBuilder() } doReturn requestBuilder
    }
    private val chain: Interceptor.Chain = mock {
        on { request() } doReturn request
        on { proceed(any()) } doReturn mock()
    }
    private val requestBuilder: Request.Builder = mock {
        on { build() } doReturn mock()
    }

    private val interceptor = HeaderInterceptor(deviceManager, storage)

    @Test
    fun `make sure all headers all added`() {
        val authData = AuthTokenDataFactory.createAuthModel()
        whenever(deviceManager.getLocale()).thenReturn("in")
        whenever(deviceManager.getOsVersionName()).thenReturn("O")
        whenever(deviceManager.getAppVersionName()).thenReturn("1.0.0")
        whenever(storage.getAccessToken()).thenReturn(authData.accessToken)

        interceptor.intercept(chain)

        verify(storage).getAccessToken()
        verify(deviceManager).getAppVersionName()
        verify(deviceManager).getOsVersionName()
        verify(deviceManager).getLocale()

        verify(requestBuilder).addHeader("Accept-Language", "in")
        verify(requestBuilder).addHeader("User-agent", "simulacra/1.0.0 Android/O")
        verify(requestBuilder).addHeader("Authorization", "Bearer ${authData.accessToken}")
        verify(requestBuilder).build()
        verifyNoMoreInteractions(deviceManager, storage, requestBuilder)
    }

}