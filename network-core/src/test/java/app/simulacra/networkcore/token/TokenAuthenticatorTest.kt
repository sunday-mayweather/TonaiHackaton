package app.simulacra.networkcore.token

import app.simulacra.networkcore.datafactory.AuthTokenDataFactory
import app.simulacra.networkcore.token.storage.TokenStorage
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Call
import retrofit2.Response

@ExtendWith(MockitoExtension::class)
internal class TokenAuthenticatorTest {

    private val tokenApi: TokenRestApi = mock()
    private val tokenStorage: TokenStorage = mock()
    private val failedRequest: okhttp3.Response = mock()
    private val newTokenRequest: Call<AuthTokenModel> = mock()
    private val newTokenResponse: Response<AuthTokenModel> = mock()

    private val authenticator = TokenAuthenticator(tokenApi, tokenStorage)

    @Test
    fun `token is requested and stored`() {
        val refreshToken = "test_token"
        val authModel = AuthTokenDataFactory.createAuthModel()
        whenever((tokenStorage.getRefreshToken())).thenReturn(refreshToken)
        whenever((tokenApi.refreshAccessToken(refreshToken))).thenReturn(newTokenRequest)
        whenever((newTokenRequest.execute())).thenReturn(newTokenResponse)
        whenever((newTokenResponse.isSuccessful)).thenReturn(true)
        whenever((newTokenResponse.body())).thenReturn(authModel)
        whenever((failedRequest.request)).thenReturn(mock())

        val retryRequest = authenticator.authenticate(null, failedRequest)

        verify(failedRequest).request
        verify(tokenStorage).setAccessToken(authModel.accessToken)
        verify(tokenStorage).getRefreshToken()
        verify(tokenApi).refreshAccessToken(refreshToken)
        assertNotNull(retryRequest)

        verifyNoMoreInteractions(tokenStorage, tokenApi, failedRequest)
    }

    @Test
    fun `verify token is requested but not received`() {
        val refreshToken = "test_token"
        whenever((tokenStorage.getRefreshToken())).thenReturn(refreshToken)
        whenever((tokenApi.refreshAccessToken(refreshToken))).thenReturn(newTokenRequest)
        whenever((newTokenRequest.execute())).thenReturn(newTokenResponse)
        whenever((newTokenResponse.isSuccessful)).thenReturn(false)
        whenever((newTokenResponse.code())).thenReturn(500)

        val retryRequest = authenticator.authenticate(null, failedRequest)

        verify(tokenStorage).getRefreshToken()
        verify(tokenApi).refreshAccessToken(refreshToken)
        assertNull(retryRequest)

        verifyNoMoreInteractions(tokenStorage, tokenApi, failedRequest)
    }
}