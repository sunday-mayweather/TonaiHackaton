package app.simulacra.onboarding.sections

import app.simulacra.onboarding.R
import app.simulacra.featurecommon.views.Topic

sealed class ChooseTopicState {

    data class UpdatedTopicsState(val topics: Set<TopicItem>) : ChooseTopicState()

    object TopicsEmpty : ChooseTopicState()

}

data class TopicItem(
    val resId: Int,
    val topic: Topic
) {
    companion object {
        fun getAllTopics() = setOf(
            TopicItem(
                resId = R.id.chip2,
                topic = Topic.Micronutrients
            ),
            TopicItem(
                resId = R.id.chip3,
                topic = Topic.Longevity
            ),
            TopicItem(
                resId = R.id.chip4,
                topic = Topic.Cognitive
            ),
            TopicItem(
                resId = R.id.chip5,
                topic = Topic.Muscle
            ),
            TopicItem(
                resId = R.id.chip6,
                topic = Topic.Sleep
            )
        )
    }
}