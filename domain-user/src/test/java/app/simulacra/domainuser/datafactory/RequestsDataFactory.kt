package app.simulacra.domainuser.datafactory

import app.simulacra.domainuser.interactors.LoginRequest
import app.simulacra.domainuser.interactors.UpdateUserRequest

object RequestsDataFactory {
    
    fun createLoginRequest(): LoginRequest {
        return LoginRequest(isGuest = true)
    }

    fun createUpdateUseRequest(): UpdateUserRequest {
        return UpdateUserRequest(nickName = "Test", email = "test@mail.com", password = "Test")
    }
}