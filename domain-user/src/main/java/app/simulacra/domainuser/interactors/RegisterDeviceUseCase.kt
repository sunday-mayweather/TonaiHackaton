package app.simulacra.domainuser.interactors

import app.simulacra.domaincore.interactor.DefaultIoDispatcherUseCase
import app.simulacra.domainuser.models.Device
import app.simulacra.domainuser.repository.UserRepository
import javax.inject.Inject

class RegisterDeviceUseCase @Inject constructor(
    private val repository: UserRepository
): DefaultIoDispatcherUseCase<Device>() {

    override suspend fun invoke() = repository.getDevice()

}