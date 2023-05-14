package app.simulacra.featurecommon.utils.views

import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

inline fun Fragment.handleBackPressed(autoDispose: Boolean = true, crossinline action: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            action()
            if (autoDispose) {
                remove()
            }
        }
    })
}

fun Fragment.checkSelfPermissionCompat(permission: String) =
    ContextCompat.checkSelfPermission(requireContext(), permission)

fun Fragment.shouldShowRequestPermissionRationaleCompat(permission: String) =
    shouldShowRequestPermissionRationale(permission)

fun Fragment.requestPermissionsCompat(permissionsArray: Array<String>, requestCode: Int) =
    requestPermissions(permissionsArray, requestCode)