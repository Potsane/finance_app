package com.app.financeapp.binding

import android.annotation.SuppressLint
import android.os.Build
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.financeapp.R
import com.app.financeapp.extension.toTwoDecimalPlaces
import com.app.financeapp.util.getFormattedDate

@BindingAdapter("formatDate")
fun formatDate(view: TextView, date: String?) {
    date?.let {
        view.text = getFormattedDate(it)
    } ?: return
}

@SuppressLint("SetTextI18n")
@BindingAdapter("percentageChange")
fun setPercentageChange(view: TextView, value: Double?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        value?.let {
            if (it > 20.0) {
                val color = R.color.green
                view.setTextColor(view.context.getColor(color))
            } else {
                val color = R.color.red
                view.setTextColor(view.context.getColor(color))
            }
            view.text = "${value.toTwoDecimalPlaces()}%"
        } ?: return
    }
}