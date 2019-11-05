package ua.olorin.helper.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ua.olorin.helper.R
import ua.olorin.helper.data.Service

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(isVisible: Boolean){
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("url")
    fun ImageView.url(url: String){
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.overrideOf(200))
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("workDays")
    fun TextView.workDays(workDays: List<Int>){
        var result = ""
        val daysOfWeek = resources.getStringArray(R.array.days_of_week)
        for (value in workDays){
            result += daysOfWeek[value] + ' '
        }

        text = result
    }

    @JvmStatic
    @BindingAdapter("services")
    fun LinearLayout.services(services: List<Service>){
        removeAllViews()

        for (value in services){
            val horizontalLayout = LinearLayout(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT).apply {
                    if (services.size == services.indexOf(value) + 1){
                        setMargins(0,
                            convertDpToPixel(context, 16f), 0, 0)
                    }
                }
                orientation = LinearLayout.HORIZONTAL
            }

            val label = TextView(context).apply {
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                params.weight = 1f

                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                }
                setTextColor(ContextCompat.getColor(context,
                    R.color.colorPrimaryText
                ))
                text = value.label
            }

            horizontalLayout.addView(label)

            val number = TextView(context).apply {

                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)

                setTextColor(ContextCompat.getColor(context,
                    R.color.colorPrimaryText
                ))
                text = resources.getString(R.string.text_service_price, value.number)
            }

            horizontalLayout.addView(number)

            addView(horizontalLayout)
        }
    }

    private fun convertDpToPixel(context: Context, dp: Float): Int{
        return (dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }
}