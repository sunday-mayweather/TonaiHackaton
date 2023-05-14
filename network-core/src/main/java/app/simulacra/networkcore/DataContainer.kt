package app.simulacra.networkcore

import retrofit2.Response
import timber.log.Timber

data class DataContainer<T>(
    var data: T? = null,
    var dataState: DataState,
    var throwable: Throwable? = null
)

enum class DataState {
    // Status code from >= 200 && < 300
    FRESH,
    // Status code is 304 - etag not changed
    NOT_MODIFIED,
    // An error has occurred, we need to re-fetch the data
    ERROR
}

inline fun <R> dataContainerOf(block: (() -> Response<R>)): DataContainer<R> {
    return runCatching(block)
        .fold({
            it.retrieve()
        }, {
            Timber.e(it, "DataContainer error: failed to retrieve data!")
            throw it
        })
}