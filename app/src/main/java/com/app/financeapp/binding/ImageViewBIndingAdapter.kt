package com.app.financeapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageFromUrl")
fun setImageFromUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)
}