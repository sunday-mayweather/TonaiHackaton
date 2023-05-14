package app.simulacra.featurecommon.utils.navigation

import androidx.navigation.NavOptions
import app.simulacra.featurecommon.R

import timber.log.Timber

/**
 * We wrap the code block in this method in order to prevent crashes which might be caused
 * by simultaneous navigation actions (> 1) calls. It is applicable only to the screens where
 * more than one navigation actions might be called at the same time.
 *
 * https://github.com/googlesamples/android-sunflower/issues/239
 *
 * This issue might be either caused by Navigation Components or AndroidX Fragments.
 *
 * https://issuetracker.google.com/issues/118975714
 *
 * For now, 1.1.0-alpha1 version of Fragment AndroidX dependency doesn't help, but this might be fixed
 * in the future. As soon, as it's fixed we should stop using this method.
 */
inline fun executeSafeNavAction(block: () -> Unit) {
    try {
        block()
    } catch (exc: IllegalArgumentException) {
        Timber.e(exc, "Could not create new fragment instance")
    }
}

/**
 * Creates [NavOptions] and applies projects-specified transition animation for deep links navigation.
 */
fun NavOptions.Builder.applyRightToLeftAnimations(): NavOptions {
    return this.setEnterAnim(R.anim.enter_from_right)
        .setExitAnim(R.anim.exit_to_left)
        .setPopEnterAnim(R.anim.enter_from_left)
        .setPopExitAnim(R.anim.exit_to_right)
        .build()
}

/**
 * Creates [NavOptions] and applies projects-specified transition animation for deep links navigation.
 */
fun NavOptions.Builder.applyBottomToTopAnimations(): NavOptions {
    return this.setEnterAnim(R.anim.slide_in_bottom)
        .setExitAnim(R.anim.slide_out_top)
        .setPopEnterAnim(R.anim.slide_in_top)
        .setPopExitAnim(R.anim.slide_out_bottom)
        .build()
}