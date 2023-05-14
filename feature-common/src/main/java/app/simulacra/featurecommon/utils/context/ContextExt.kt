package app.simulacra.featurecommon.utils.context

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import app.simulacra.featurecommon.R
import timber.log.Timber

fun Context.openLink(link: String) {
    val builder = CustomTabsIntent.Builder()
    builder.setStartAnimations(this, R.anim.enter_from_right, R.anim.exit_to_left)
    builder.setExitAnimations(this, R.anim.enter_from_left, R.anim.exit_to_right)
    try {
        val build = builder.build()
        build.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        build.launchUrl(this, link.toUri())
    } catch (exception: ActivityNotFoundException) {
        Timber.e(exception, "Failed to open link: $link")
    } catch (exception: Exception) {
        openLinkWithViewIntent(link)
    }
}

fun Context.openLinkWithViewIntent(link: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    })
}
