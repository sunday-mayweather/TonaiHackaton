package app.simulacra.featurecommon.utils.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * Skips provided number of events. Useful if you don't need [LiveData] latest event be triggered.
 */
fun <T> LiveData<T>.skip(count: Int): LiveData<T> {
    val mutableLiveData: MediatorLiveData<T> = MediatorLiveData()
    var skippedCount = 0
    mutableLiveData.addSource(this) {
        if (skippedCount >= count) {
            mutableLiveData.value = it
        }
        skippedCount++
    }
    return mutableLiveData
}