package app.simulacra.datadiscover.data

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import timber.log.Timber
import javax.inject.Inject

interface StoriesJsonStore {

    suspend fun selectStories(): List<SummaryModel>

}

class StoriesJsonStoreImpl @Inject constructor(
    private val moshi: Moshi,
    private val context: Context
) : StoriesJsonStore {

    companion object {
        private const val STORIES_JSON_PATH = "stories.json"
        private val storiesCache = mutableListOf<SummaryModel>()
    }

    override suspend fun selectStories(): List<SummaryModel> = withContext(Dispatchers.IO) {
        val type = Types.newParameterizedType(List::class.java, SummaryModel::class.java)
        val adapter: JsonAdapter<List<SummaryModel>> = moshi.adapter(type)
        return@withContext if (storiesCache.isNotEmpty()) {
            storiesCache
        } else {
            try {
                @Suppress("BlockingMethodInNonBlockingContext")
                storiesCache.plus(adapter.fromJson(
                    context.assets.open(STORIES_JSON_PATH).bufferedReader().use { it.readText() }
                ).orEmpty())
            } catch (error: IOException) {
                Timber.e(error, "Failed to fetch stories json!")
                emptyList()
            }
        }
    }
}