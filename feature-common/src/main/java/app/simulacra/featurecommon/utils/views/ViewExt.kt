package app.simulacra.featurecommon.utils.views

import android.animation.AnimatorInflater
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.text.underline
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView
import app.simulacra.featurecommon.R
import com.google.android.material.snackbar.Snackbar

/**
 * Sets visibility to [View.VISIBLE].
 */
fun View.visible() {
    if (isVisible) {
        return
    }
    visibility = View.VISIBLE
}

/**
 * Sets visibility to [View.GONE].
 */
fun View.gone() {
    if (!isVisible) {
        return
    }
    visibility = View.GONE
}

/**
 * Applies formatting and listeners to the [AppCompatTextView].
 */
inline fun AppCompatTextView.applyPoliciesFormatting(
    crossinline termsClickListener: (() -> Unit),
    crossinline privacyPolicyClickListener: (() -> Unit)
) {
    val builder = buildSpannedString {
        append(context.getString(R.string.legal_policy_lbl))
        append(" ")
        underline {
            color(ContextCompat.getColor(context, R.color.pure_gold)) {
                clickable({ termsClickListener() }) {
                    append(context.getString(R.string.legal_terms_lbl))
                }
            }
        }
        append(" ")
        append(context.getString(R.string.legal_and_lbl))
        append(" ")
        underline {
            color(ContextCompat.getColor(context, R.color.pure_gold)) {
                clickable({ privacyPolicyClickListener() }) {
                    append(context.getString(R.string.legal_privacy_lbl))
                }
            }
        }
    }

    this.apply {
        highlightColor = Color.TRANSPARENT
        text = builder
        movementMethod = LinkMovementMethod.getInstance()
    }
}

/**
 * Changes background of the [Snackbar].
 */
@Suppress("unused")
fun Snackbar.withBackground(@ColorInt colorInt: Int): Snackbar{
    this.view.setBackgroundColor(colorInt)
    return this
}

/**
 * Changes text color of the [Snackbar].
 */
@Suppress("unused")
fun Snackbar.withTextColor(@ColorInt colorInt: Int): Snackbar{
    val tv = view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    tv.setTextColor(colorInt)
    return this
}

/**
 * Sets [ContentLoadingProgressBar] visibility and shows animation.
 */
fun ContentLoadingProgressBar.start() {
    this.visible()
    this.show()
}

/**
 * Creates custom [ColorStateList] with checked and unchecked states.
 *
 * @param checkedColor - selection color of the checked item.
 * @param uncheckedColor - selection color of the unchecked item.
 */
fun applyColors(@ColorInt checkedColor: Int, @ColorInt uncheckedColor: Int): ColorStateList {
    return ColorStateList(
        arrayOf(
            intArrayOf(-android.R.attr.state_checked),  // Unchecked
            intArrayOf(android.R.attr.state_checked)    // Checked
        ),
        intArrayOf(
            uncheckedColor,     // The color for the Unchecked state
            checkedColor        // The color for the Checked state
        )
    )
}

/**
 * Attaches RecyclerView to toolbar so when it scrolls elevation changes
 * (at top = 0dp elevation, added when scroll starts)
 */
fun RecyclerView.handleToolbarElevation(toolbar: Toolbar) {
    toolbar.stateListAnimator = AnimatorInflater.loadStateListAnimator(toolbar.context, R.animator.toolbar_animator)
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            toolbar.isSelected = canScrollVertically(-1)
        }
    })
}
