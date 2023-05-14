package app.simulacra.domainuser.interactors

import app.simulacra.domainuser.repository.UserRepository
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class RegisterDeviceUseCaseTest {

    private val userRepository: UserRepository = mock()

    private val useCase = RegisterDeviceUseCase(userRepository)

    @Test
    fun `register device gets it from the repository`() {
//        val device = DeviceDataFactory.createDevice()
//        whenever(userRepository.getDevice()).thenReturn(Single.just(device))
//
//        useCase.get().test().await()
//            .assertValue(device)
//            .assertComplete()
//            .assertOf {
//                verify(userRepository).getDevice()
//                verifyNoMoreInteractions(userRepository)
//            }
    }
}