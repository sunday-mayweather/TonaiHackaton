package app.simulacra.discover.sections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.simulacra.discover.navigation.DiscoverCoordinator
import app.simulacra.domaincore.error.ErrorType
import app.simulacra.domaincore.interactor.Result
import app.simulacra.domaindiscover.GetSummariesUseCase
import app.simulacra.domaindiscover.Summary
import kotlinx.coroutines.launch
import timber.log.Timber

class DiscoverViewModel @Inject constructor(
    private val coordinator: DiscoverCoordinator,
    private val getSummariesUseCase: GetSummariesUseCase
): ViewModel() {

    private val contentMutableLiveData = MutableLiveData<DiscoverState>()
    val contentLiveData: LiveData<DiscoverState> = contentMutableLiveData

    fun loadSummaries() {
        // Start with loading
        contentMutableLiveData.postValue(DiscoverState.Loading)
        viewModelScope.launch {
            when (val result = getSummariesUseCase.get()) {
                is Result.Success -> {
                    Timber.d("Loaded summaries: ${result.data}")
                    contentMutableLiveData.postValue(DiscoverState.Loaded(result.data.toList()))
                }
                is Result.Error -> {
                    contentMutableLiveData.postValue(DiscoverState.Error(ErrorType.UNKNOWN))
                }
            }
        }
    }

    fun openSummary(summary: Summary) {
        // Handle open
        coordinator.openSummaryDetails(summary.sourceUrl)
    }
}