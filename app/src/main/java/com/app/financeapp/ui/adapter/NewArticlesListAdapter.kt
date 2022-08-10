package com.app.financeapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.financeapp.R
import com.app.financeapp.databinding.ItemNewsArticleCardBinding
import com.app.financeapp.network.model.NewsArticle
import com.app.financeapp.BR

class NewArticlesListAdapter(private val items: MutableList<NewsArticle>) :
    RecyclerView.Adapter<NewArticlesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemNewsArticleCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news_article_card,
            parent,
            false
        )
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: NewArticlesListAdapter.ViewHolder, position: Int) {
        val item: NewsArticle = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    fun updateItems(updatedList: List<NewsArticle>) {
        val diffUtil = NewArticlesListDiffUtil(items, updatedList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        items.clear()
        items.addAll(updatedList)
        diffResults.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(
        view: View,
        private val binding: ItemNewsArticleCardBinding
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: NewsArticle) {
            binding.setVariable(BR.article, item)
            binding.executePendingBindings()
        }
    }
}