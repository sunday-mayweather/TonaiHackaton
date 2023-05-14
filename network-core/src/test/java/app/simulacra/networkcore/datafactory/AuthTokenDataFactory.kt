package app.simulacra.networkcore.datafactory

import app.simulacra.networkcore.token.AuthTokenModel

object AuthTokenDataFactory {

    fun createAuthModel(): AuthTokenModel {
        return AuthTokenModel(accessToken = "testAccessToken", refreshToken = "testRefreshToken")
    }

}