package com.app.financeapp.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.app.financeapp.ui.main.clickliestener.NewsArticleClickListener
import com.app.financeapp.BR

@BindingAdapter(
    value = ["items", "itemsLayout", "itemsClickListener"],
    requireAll = false
)
fun <T> setListItems(
    viewGroup: ViewGroup,
    items: List<T>?,
    layout: Int,
    onItemClickListener: NewsArticleClickListener? = null
) {

    viewGroup.removeAllViews()
    if (items.isNullOrEmpty()) return

    val inflater = LayoutInflater.from(viewGroup.context)
    for (i in items.indices) {
        val item = items[i]
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            layout,
            viewGroup,
            true
        )
        onItemClickListener?.let { binding?.setVariable(BR.clickListener, it) }
        binding?.setVariable(BR.article, item)
        binding?.executePendingBindings()
    }
}