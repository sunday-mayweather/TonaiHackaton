package app.simulacra.domaindiscover

interface DiscoverRepository {
    suspend fun getSummaries(): Set<Summary>
}