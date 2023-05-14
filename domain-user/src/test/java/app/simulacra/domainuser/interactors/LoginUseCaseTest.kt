package app.simulacra.domainuser.interactors

import app.simulacra.domainuser.repository.UserRepository
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class LoginUseCaseTest {

    private val userRepository: UserRepository = mock()

    private var useCase = LoginUseCase(userRepository)

    @Test
    fun `login succeed`() {
//        val request = RequestsDataFactory.createLoginRequest()
//        whenever(userRepository.login(request)).thenReturn(Completable.complete())
//
//        useCase.get(request).test().await()
//            .assertComplete()
//            .assertOf {
//                verify(userRepository).login(request)
//                verifyNoMoreInteractions(userRepository)
//            }
    }
}