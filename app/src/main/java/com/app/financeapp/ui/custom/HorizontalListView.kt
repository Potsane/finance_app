package com.app.financeapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.app.financeapp.R
import com.app.financeapp.BR
import com.app.financeapp.binding.setImageFromUrl
import com.app.financeapp.databinding.HorizontalListViewBinding
import com.app.financeapp.databinding.ItemTopNewsArticleCardBinding
import com.app.financeapp.network.model.NewsArticle
import com.app.financeapp.ui.main.clickliestener.NewsArticleClickListener

class HorizontalListView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttribute: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttribute) {

    private val binding: HorizontalListViewBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.horizontal_list_view,
        this,
        true
    )
    private var cards: List<NewsArticle>? = emptyList()
    var cardClickListener: NewsArticleClickListener? = null

    fun setClickListener(listener: NewsArticleClickListener?) {
        cardClickListener = listener
    }

    fun setArticles(items: List<NewsArticle>?) {
        cards = items
        binding.articlesContainer.apply {
            removeAllViews()
            items?.forEach { article ->
                val articleBinding = DataBindingUtil.inflate<ItemTopNewsArticleCardBinding>(
                    LayoutInflater.from(context),
                    R.layout.item_top_news_article_card,
                    this,
                    false
                )
                articleBinding.setVariable(BR.article, article)
                articleBinding?.setVariable(BR.clickListener, cardClickListener)
                articleBinding.executePendingBindings()
                addView(articleBinding.root)
            }
        }
    }
}