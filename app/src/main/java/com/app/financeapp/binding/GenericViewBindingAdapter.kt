package com.app.financeapp.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("showOrHide")
fun showOrHide(view: View, shouldShow: Boolean) {
    if (shouldShow) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}