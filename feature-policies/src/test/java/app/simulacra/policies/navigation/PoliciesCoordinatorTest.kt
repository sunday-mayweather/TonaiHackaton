package app.simulacra.policies.navigation

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class PoliciesCoordinatorTest {

    private val navigator: PoliciesNavigator = mock()

    private val coordinator: PoliciesCoordinator = PoliciesCoordinatorImpl(navigator)

    @Test
    fun `back pressed navigates back`() {
        coordinator.backPressed()

        verify(navigator).navigateBack()
        verifyNoMoreInteractions(navigator)
    }
}