package app.simulacra.onboarding.sections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.simulacra.featurecommon.views.asString
import app.simulacra.domaincore.utils.preferences.Preferences
import app.simulacra.onboarding.navigation.OnboardingCoordinator
import javax.inject.Inject

class ChooseTopicsViewModel @Inject constructor(
    private val preferences: Preferences,
    private val coordinator: OnboardingCoordinator
) : ViewModel() {

    private val contentMutableLiveData: MutableLiveData<ChooseTopicState> = MutableLiveData()

    val contentLiveData: LiveData<ChooseTopicState> = contentMutableLiveData

    fun updateTopics(topicResIds: List<Int>) {
        // Hackatonish shitty code
        val resultTopics = TopicItem.getAllTopics().filter { topicResIds.contains(it.resId) }.toSet()
        contentMutableLiveData.value = ChooseTopicState.UpdatedTopicsState(resultTopics)
    }

    fun continueClick() {
        val topicsValue = contentLiveData.value
        if (topicsValue is ChooseTopicState.UpdatedTopicsState && topicsValue.topics.isNotEmpty()) {
            // Hackatonish shitty code
            preferences.saveTopics(topicsValue.topics.map { it.topic.asString() }.toSet())
            coordinator.openMain()
        } else {
            contentMutableLiveData.postValue(ChooseTopicState.TopicsEmpty)
        }
    }
}