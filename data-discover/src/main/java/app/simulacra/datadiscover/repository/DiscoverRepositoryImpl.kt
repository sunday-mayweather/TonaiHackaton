package app.simulacra.datadiscover.repository

import app.simulacra.datadiscover.data.StoriesJsonStore
import app.simulacra.datadiscover.data.SummaryModel
import javax.inject.Inject
import app.simulacra.domaindiscover.DiscoverRepository
import app.simulacra.domaindiscover.Summary

class DiscoverRepositoryImpl @Inject constructor(
    private val jsonStore: StoriesJsonStore
) : DiscoverRepository {

    override suspend fun getSummaries(): Set<Summary> {
        return jsonStore.selectStories().map { mapModel(it) }.toSet()
    }

    private fun mapModel(model: SummaryModel) : Summary {
        return Summary(
            title = model.title,
            sourceUrl = model.sourceUrl,
            tags = model.tags.toSet(),
            summaryShort = model.summary.take(400),
            summary = model.summary
        )
    }
}