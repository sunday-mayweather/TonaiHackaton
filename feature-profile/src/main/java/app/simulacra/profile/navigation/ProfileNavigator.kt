package app.simulacra.profile.navigation

import android.content.res.Resources
import androidx.navigation.NavController
import dagger.Lazy
import javax.inject.Inject

interface ProfileNavigator {

    fun navigateToSettings()

}

class ProfileNavigatorImpl @Inject constructor(
    private val resources: Resources,
    private val navController: Lazy<NavController>
): ProfileNavigator {

    override fun navigateToSettings() {

    }
}