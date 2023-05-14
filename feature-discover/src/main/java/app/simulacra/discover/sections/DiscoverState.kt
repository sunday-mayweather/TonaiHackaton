package app.simulacra.discover.sections

import app.simulacra.domaincore.error.ErrorType
import app.simulacra.domaindiscover.Summary

sealed class DiscoverState {

    object Empty : DiscoverState()

    object Loading : DiscoverState()

    data class Error(val errorType: ErrorType) : DiscoverState()

    data class Loaded(val data: List<Summary>) : DiscoverState()
}