package app.simulacra.featurecommon.utils.validators

object EmailValidator {

    // Will work for most cases, but not for all possible variations
    val EMAIL_REGEX = "^[\\w!#\$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#\$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}\$".toRegex()

}