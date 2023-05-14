package app.simulacra.domainuser.interactors

import app.simulacra.domainuser.repository.UserRepository
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class CreateProfileUseCaseTest {

    private val userRepository: UserRepository = mock()

    private val useCase = CreateProfileUseCase(userRepository)

    @Test
    fun `create profile succeed`() {
//        val request = RequestsDataFactory.createUpdateUseRequest()
//        whenever(userRepository.saveUser(request)).thenReturn(Completable.complete())
//
//        useCase.get(request).test().await()
//            .assertComplete()
//            .assertOf {
//                verify(userRepository).saveUser(request)
//                verifyNoMoreInteractions(userRepository)
//            }
    }
}