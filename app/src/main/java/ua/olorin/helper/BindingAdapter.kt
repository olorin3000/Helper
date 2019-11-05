package ua.olorin.helper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.content_recycler_view.view.*

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
}