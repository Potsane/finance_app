package com.app.financeapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.app.financeapp.BR
import com.app.financeapp.R
import com.app.financeapp.databinding.HorizontalListViewBinding
import com.app.financeapp.databinding.ItemStockTickerCardBinding
import com.app.financeapp.databinding.ItemTopNewsArticleCardBinding
import com.app.financeapp.network.model.NewsArticle
import com.app.financeapp.network.model.StockTicker
import com.app.financeapp.ui.main.clicklistener.NewsArticleClickListener

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

    var cardClickListener: NewsArticleClickListener? = null

    fun setClickListener(listener: NewsArticleClickListener?) {
        cardClickListener = listener
    }

    fun setStockTickers(items: List<StockTicker>?){
        binding.articlesContainer.apply {
            removeAllViews()
            items?.forEach { stock ->
                val stockTickerBinding = DataBindingUtil.inflate<ItemStockTickerCardBinding>(
                    LayoutInflater.from(context),
                    R.layout.item_stock_ticker_card,
                    this,
                    false
                )
                stockTickerBinding.setVariable(BR.stock, stock)
                stockTickerBinding.executePendingBindings()
                addView(stockTickerBinding.root)
            }
        }
    }

    fun setArticles(items: List<NewsArticle>?) {
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