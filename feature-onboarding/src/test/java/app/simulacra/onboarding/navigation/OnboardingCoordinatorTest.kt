package app.simulacra.onboarding.navigation

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class OnboardingCoordinatorTest {

    private val navigator: OnboardingNavigator = mock()
    private val coordinator: OnboardingCoordinator = OnboardingCoordinatorImpl(navigator)

    @Test
    fun `open login navigates to login`() {
        coordinator.openLogin()

        verify(navigator).navigateToLogin()
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun `open policy navigates to policy`() {
        coordinator.openPolicy()

        verify(navigator).navigateToPolicy()
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun `open create account navigates to create account`() {
        coordinator.openCreateAccount()

        verify(navigator).navigateToCreateAccount()
        verifyNoMoreInteractions(navigator)
    }

    @Test
    fun `open main navigates to main`() {
        coordinator.openMain()

        verify(navigator).navigateToMain()
        verifyNoMoreInteractions(navigator)
    }
}