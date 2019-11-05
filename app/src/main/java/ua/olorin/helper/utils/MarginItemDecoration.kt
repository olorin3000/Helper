package ua.olorin.helper.utils

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceHeight: Float = 16f,
                           private val spaceWidth: Float = 16f,
                           private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {

        val horizontal = getPaddingPx(spaceWidth)
        val vertical = getPaddingPx(spaceHeight)

        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = vertical
            }
            left =  horizontal
            right = horizontal
            bottom = vertical
        }
    }

    private fun getPaddingPx(dp: Float) : Int{
        val metrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics).toInt()
    }
}