package app.simulacra.networkcore

import okhttp3.Call
import okhttp3.Request
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Extension function, that helps to deal with Etags.
 *
 * @return a [DataContainer] which contains a [DataState] and/or data of type [T].
 **/
fun <T> Response<out T>.retrieve(): DataContainer<T> =
    when (this.raw().networkResponse?.code) {
        200, 201 -> DataContainer(data = this.body(), dataState = DataState.FRESH)
        304 -> DataContainer(data = this.body(), dataState = DataState.NOT_MODIFIED)
        // This code is used to signify that server has not finished generating the response, should try later
        202 -> DataContainer(dataState = DataState.ERROR, throwable = RetryException(this.message()))
        else -> DataContainer(dataState = DataState.ERROR, throwable = HttpException(this))
    }

@Suppress("unused")
@PublishedApi
internal inline fun Retrofit.Builder.callFactory(
    crossinline body: (Request) -> Call
) = callFactory { request -> body(request) }

class RetryException(message: String) : Exception(message)