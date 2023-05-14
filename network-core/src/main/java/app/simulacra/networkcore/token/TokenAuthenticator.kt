package app.simulacra.networkcore.token

import app.simulacra.networkcore.token.storage.TokenStorage
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator (
    private val tokenApi: TokenRestApi,
    private val tokenStorage: TokenStorage
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        //refresh token as retrofit synchronous call
        val result = tokenApi.refreshAccessToken(tokenStorage.getRefreshToken()).execute()
        return if (result.isSuccessful) {
            result.body()?.accessToken?.let {
                tokenStorage.setAccessToken(it)
            }
            response.request
        } else {
            //server determines refresh token is no longer valid, should refresh refresh token
            null
        }
    }

}