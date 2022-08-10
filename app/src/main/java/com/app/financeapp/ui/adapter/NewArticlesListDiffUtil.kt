package com.app.financeapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.app.financeapp.network.model.NewsArticle

class NewArticlesListDiffUtil(
    private val currentItems: List<NewsArticle>,
    private val updatedItems: List<NewsArticle>
) :   DiffUtil.Callback(){

    override fun getOldListSize() = currentItems.size

    override fun getNewListSize() = updatedItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return currentItems[oldItemPosition].title == updatedItems[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            currentItems[oldItemPosition].title != updatedItems[newItemPosition].title -> false
            currentItems[oldItemPosition].author != updatedItems[newItemPosition].author -> false
            currentItems[oldItemPosition].description != updatedItems[newItemPosition].description -> false
            currentItems[oldItemPosition].url != updatedItems[newItemPosition].url -> false
            currentItems[oldItemPosition].urlToImage != updatedItems[newItemPosition].urlToImage -> false
            currentItems[oldItemPosition].publishedAt != updatedItems[newItemPosition].publishedAt -> false
            else -> true
        }
    }
}