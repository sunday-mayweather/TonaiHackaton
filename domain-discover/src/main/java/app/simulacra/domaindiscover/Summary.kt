package app.simulacra.domaindiscover

data class Summary(
    val title: String,
    val sourceUrl: String,
    val tags: Set<String>,
    val summary: String,
    val summaryShort: String
)