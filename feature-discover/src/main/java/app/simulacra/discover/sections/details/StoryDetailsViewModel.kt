package app.simulacra.discover.sections.details

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.simulacra.domaincore.error.ErrorType
import app.simulacra.domaincore.interactor.Result
import app.simulacra.domaindiscover.GetSummariesUseCase
import timber.log.Timber

class StoryDetailsViewModel @Inject constructor(
    private val getSummariesUseCase: GetSummariesUseCase
) : ViewModel() {

    private val contentMutableLiveData = MutableLiveData<StoryDetailsState>()
    val contentLiveData: LiveData<StoryDetailsState> = contentMutableLiveData

    fun loadStoryDetails(sourceUrl: String) {
        viewModelScope.launch {
            when (val result = getSummariesUseCase.get()) {
                is Result.Success -> {
                    val details = result.data.first { it.sourceUrl == sourceUrl }
                    contentMutableLiveData.postValue(StoryDetailsState.Loaded(details))
                }
                is Result.Error -> {
                    contentMutableLiveData.postValue(StoryDetailsState.Error(ErrorType.UNKNOWN))
                }
            }
        }
    }
}