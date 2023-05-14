package app.simulacra.datadiscover.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SummaryModel(
    @Json(name="title")
    val title: String,
    @Json(name="tags")
    val tags: List<String>,
    @Json(name="summary")
    val summary: String,
    @Json(name="sourceUrl")
    val sourceUrl: String,
    @Json(name="rawText")
    val rawText: String
)