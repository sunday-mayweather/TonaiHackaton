package app.simulacra.featurecommon.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * https://rubensousa.com/2019/08/16/nested_recyclerview_part1/
 * A RecyclerView that only handles scroll events with the same orientation of its LayoutManager.
 * Avoids situations where nested recyclerviews don't receive touch events properly.
 */
open class OrientationAwareRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private var lastX = 0.0f
    private var lastY = 0.0f
    private var scrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            scrolling = newState != SCROLL_STATE_IDLE
        }
    }

    init {
        addOnScrollListener(scrollListener)
    }

    override fun onDetachedFromWindow() {
        removeOnScrollListener(scrollListener)
        super.onDetachedFromWindow()
    }

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        val lm = layoutManager ?: return super.onInterceptTouchEvent(e)

        var allowScroll = true

        when (e.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastX = e.x
                lastY = e.y
                // If we were scrolling, stop now by faking a touch release
                if (scrolling) {
                    val newEvent = MotionEvent.obtain(e)
                    newEvent.action = MotionEvent.ACTION_UP
                    return super.onInterceptTouchEvent(newEvent)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                // We're moving, so check if we're trying
                // to scroll vertically or horizontally so we don't intercept the wrong event.
                val currentX = e.x
                val currentY = e.y
                val dx = abs(currentX - lastX)
                val dy = abs(currentY - lastY)
                allowScroll = if (dy > dx) lm.canScrollVertically() else lm.canScrollHorizontally()
            }
        }

        return if (!allowScroll) {
            false
        } else super.onInterceptTouchEvent(e)

    }
}