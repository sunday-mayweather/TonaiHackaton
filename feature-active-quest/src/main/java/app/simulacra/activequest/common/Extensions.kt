package app.simulacra.activequest.common

import app.simulacra.activequest.R
import com.google.ar.core.exceptions.UnavailableException
import com.google.ar.core.exceptions.UnavailableSdkTooOldException
import com.google.ar.core.exceptions.UnavailableApkTooOldException
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException

fun UnavailableException?.message(): Int {
    return when (this) {
        is UnavailableArcoreNotInstalledException -> R.string.exception_arcore_not_installed
        is UnavailableApkTooOldException -> R.string.exception_apk_too_old
        is UnavailableSdkTooOldException -> R.string.exception_sdk_too_old
        is UnavailableDeviceNotCompatibleException -> R.string.exception_device_not_compatible
        is UnavailableUserDeclinedInstallationException -> R.string.exception_user_declined_installation
        else -> R.string.exception_unknown
    }
}