package app.simulacra.profile.sections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.simulacra.domaincore.utils.preferences.Preferences
import app.simulacra.featurecommon.views.asString
import app.simulacra.featurecommon.views.toTopic
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {

    private val contentMutableLiveData: MutableLiveData<ProfileState> = MutableLiveData()

    val contentLiveData: LiveData<ProfileState> = contentMutableLiveData

    fun loadTopics() {
        // Hackatonish shitty code
        val topics = preferences.getTopics().map { it.toTopic() }
        val topicItems = TopicItem.getAllTopics().filter { topics.contains(it.topic) }.toSet()
        contentMutableLiveData.value = ProfileState.UpdatedTopicsState(topicItems)
    }

    fun updateTopics(topicResIds: List<Int>) {
        // Hackatonish shitty code
        val resultTopics = TopicItem.getAllTopics().filter { topicResIds.contains(it.resId) }.toSet()
        contentMutableLiveData.value = ProfileState.UpdatedTopicsState(resultTopics)
        preferences.saveTopics(resultTopics.map { it.topic.asString() }.toSet())
    }

}