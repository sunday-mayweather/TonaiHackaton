package app.simulacra.featurecommon.utils.views

import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.text.inSpans

internal class KtxClickableSpan(private val listener: () -> Unit) : ClickableSpan() {
    override fun onClick(widget: View) {
        listener()
    }
}

fun SpannableStringBuilder.clickable(
    listener: () -> Unit,
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder {
    return inSpans(KtxClickableSpan(listener), builderAction = builderAction)
}