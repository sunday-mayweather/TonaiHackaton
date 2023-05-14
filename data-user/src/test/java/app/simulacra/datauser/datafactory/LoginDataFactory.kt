package app.simulacra.datauser.datafactory

import app.simulacra.datauser.rest.models.response.LoginResponseModel
import app.simulacra.domainuser.interactors.LoginRequest

object LoginDataFactory {

    fun createLoginRequest(): LoginRequest {
        return LoginRequest(email = "test@mail.com", password = "test", isGuest = false)
    }

    fun createLoginResponseModel(): LoginResponseModel {
        return LoginResponseModel(accessToken = "test_at", refreshToken = "test_rt")
    }
}