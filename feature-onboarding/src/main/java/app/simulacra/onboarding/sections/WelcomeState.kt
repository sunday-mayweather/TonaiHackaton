package app.simulacra.onboarding.sections

sealed class WelcomeState {

    object Loading : WelcomeState()

    sealed class Error : WelcomeState() {
        object NetworkError : Error()
        object UnknownError : Error()
        data class ServerError(val errorMessage: String) : Error()
    }

    object Success : WelcomeState()
}