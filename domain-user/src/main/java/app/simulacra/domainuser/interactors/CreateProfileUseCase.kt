package app.simulacra.domainuser.interactors

import app.simulacra.domaincore.interactor.Result
import app.simulacra.domaincore.interactor.UseCaseIoDispatcherWithRequest
import app.simulacra.domainuser.repository.UserRepository
import javax.inject.Inject

class CreateProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
): UseCaseIoDispatcherWithRequest<Result<Unit>, UpdateUserRequest>() {

    override suspend fun invoke(): Result<Unit> {
        return Result.of { userRepository.getUser() }
    }
}

data class UpdateUserRequest(
    val nickName: String? = null,
    val email: String? = null,
    val password: String? = null
)