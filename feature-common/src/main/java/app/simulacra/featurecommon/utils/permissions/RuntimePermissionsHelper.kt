package app.simulacra.featurecommon.utils.permissions

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import app.simulacra.featurecommon.utils.views.checkSelfPermissionCompat
import app.simulacra.featurecommon.utils.views.requestPermissionsCompat
import app.simulacra.featurecommon.utils.views.shouldShowRequestPermissionRationaleCompat

interface RuntimePermissionsHelper {

    fun checkPermission(
        permission: String,
        permissionRequestCode: Int,
        permissionGrantedAction: (() -> Unit)?,
        permissionRationaleAction: (() -> Unit)?
    )

    fun handleRequestPermissionResult(
        permission: String,
        permissionRequestCode: Int,
        permissionGrantedAction: (() -> Unit)?,
        permissionDeniedAction: (() -> Unit)?,
        grantResults: IntArray,
        actualRequestCode: Int
    )

}

class RuntimePermissionsHelperImpl(
    private val fragment: Fragment
): RuntimePermissionsHelper {

    override fun checkPermission(
        permission: String,
        permissionRequestCode: Int,
        permissionGrantedAction: (() -> Unit)?,
        permissionRationaleAction: (() -> Unit)?
    ) {
        if (fragment.checkSelfPermissionCompat(permission) == PackageManager.PERMISSION_GRANTED) {
            permissionGrantedAction?.invoke()
        } else {
            requestPermission(permission, permissionRationaleAction, permissionRequestCode)
        }
    }

    override fun handleRequestPermissionResult(
        permission: String,
        permissionRequestCode: Int,
        permissionGrantedAction: (() -> Unit)?,
        permissionDeniedAction: (() -> Unit)?,
        grantResults: IntArray,
        actualRequestCode: Int
    ) {
        if (actualRequestCode == permissionRequestCode) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionGrantedAction?.invoke()
            } else {
                permissionDeniedAction?.invoke()
            }
        }
    }

    private fun requestPermission(
        permission: String,
        permissionRationaleAction: (() -> Unit)?,
        requestCode: Int
    ) {
        if (fragment.shouldShowRequestPermissionRationaleCompat(permission)) {
            permissionRationaleAction?.invoke()
        } else {
            fragment.requestPermissionsCompat(arrayOf(permission), requestCode)
        }
    }
}