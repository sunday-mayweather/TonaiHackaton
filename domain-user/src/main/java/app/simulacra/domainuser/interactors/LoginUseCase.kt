package app.simulacra.domainuser.interactors

import app.simulacra.domaincore.interactor.Result
import app.simulacra.domaincore.interactor.UseCaseIoDispatcherWithRequest
import app.simulacra.domainuser.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) : UseCaseIoDispatcherWithRequest<Result<Unit>, LoginRequest>() {

    override suspend fun invoke(): Result<Unit> {
        return Result.of { repository.login(request) }
    }

}

data class LoginRequest(
    val email: String? = null,
    val password: String? = null,
    val isGuest: Boolean = true
)