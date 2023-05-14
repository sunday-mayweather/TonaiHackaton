package app.simulacra.onboarding.sections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.simulacra.onboarding.navigation.OnboardingCoordinator
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val coordinator: OnboardingCoordinator
) : ViewModel() {

    private val contentMutableLiveData: MutableLiveData<WelcomeState> = MutableLiveData()

    val contentLiveData: LiveData<WelcomeState> = contentMutableLiveData

    fun continueClick() {
        coordinator.openChooseTopic()
    }

    fun policyClick() {
        coordinator.openPolicy()
    }

}