package app.simulacra.profile.navigation

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProfileCoordinatorTest {

    private val navigator: ProfileNavigator = mock()

    private val coordinator: ProfileCoordinator = ProfileCoordinatorImpl(navigator)

    @Test
    fun `open settings navigate to settings`() {
        coordinator.openSettings()

        verify(navigator).navigateToSettings()
        verifyNoMoreInteractions(navigator)
    }
}