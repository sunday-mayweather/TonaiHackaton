package app.simulacra.discover.navigation

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class DiscoverCoordinatorTest {

    private val navigator: DiscoverNavigator = mock()
    private val coordinator: DiscoverCoordinator = DiscoverCoordinatorImpl(navigator)

    @Test
    fun `open story details navigates to to story details`() {
        val storyId = 1
        coordinator.openSummaryDetails(storyId)

        verify(navigator).navigateToStoryDetails(storyId)
        verifyNoMoreInteractions(navigator)
    }
}