package app.simulacra.discover.sections.details

import app.simulacra.domaindiscover.Summary
import app.simulacra.domaincore.error.ErrorType

sealed class StoryDetailsState {

    data class Error(val errorType: ErrorType) : StoryDetailsState()

    data class Loaded(val data: Summary) : StoryDetailsState()
}