package com.app.financeapp.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.financeapp.util.getFormattedDate

@BindingAdapter("formatDate")
fun formatDate(view: TextView, date: String?) {
    date?.let {
        view.text = getFormattedDate(it)
    } ?: return
}