package app.simulacra.featurecommon.utils.views

import androidx.annotation.ColorInt
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Changes bottom navigation view text colors.
 *
 * @param checked - selection color of the checked item.
 * @param unchecked - selection color of the unchecked item.
 */
fun BottomNavigationView.applyTextColors(@ColorInt checked: Int, @ColorInt unchecked: Int) {
    this.itemTextColor = applyColors(checkedColor = checked, uncheckedColor = unchecked)
}