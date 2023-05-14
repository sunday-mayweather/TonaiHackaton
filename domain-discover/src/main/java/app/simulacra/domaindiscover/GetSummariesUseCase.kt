package app.simulacra.domaindiscover

import app.simulacra.domaincore.interactor.DefaultIoDispatcherUseCase
import app.simulacra.domaincore.interactor.Result
import app.simulacra.domaincore.utils.preferences.Preferences
import java.util.Locale
import javax.inject.Inject

class GetSummariesUseCase @Inject constructor(
    private val preferences: Preferences,
    private val repository: DiscoverRepository
) : DefaultIoDispatcherUseCase<Result<Set<Summary>>>()  {

    override suspend fun invoke(): Result<Set<Summary>> {
        return Result.of {
            val allData = repository.getSummaries()
            lookupSummariesForTopics(allData)
        }
    }

    private fun lookupSummariesForTopics(data: Set<Summary>): Set<Summary> {
        val topics = preferences.getTopics()
        val result = mutableSetOf<Summary>()
        // 0(n^2)
        topics.forEach { topic ->
            data.forEach {
                if (it.tags.contains(topic.lowercase(Locale.ROOT))) result.add(it)
            }
        }
        return result
    }
}