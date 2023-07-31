package com.example.boyner.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.boyner.databinding.ItemTopHeadLayoutBinding
import com.example.boyner.model.topHeadlinesModel.ArticleData
import com.example.boyner.util.formatTime
class TopHeadLinesAdapter : RecyclerView.Adapter<TopHeadLinesAdapter.TopHeadViewPagerViewHolder>() {

    private var topHeadLinesList: List<ArticleData> = emptyList()
    var onClickListener: ((ArticleData) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ArticleData>) {
        topHeadLinesList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadViewPagerViewHolder {
        val binding =
            ItemTopHeadLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopHeadViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopHeadViewPagerViewHolder, position: Int) {
        holder.bind(topHeadLinesList[position])
    }

    override fun getItemCount() = topHeadLinesList.size

    inner class TopHeadViewPagerViewHolder(private val binding: ItemTopHeadLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(articleData: ArticleData) {
            with(binding) {
                Glide.with(root.context)
                    .load(articleData.urlToImage)
                    .into(ivNewsImage)
                tvNewsTitle.text = articleData.title
                tvTime.text = articleData.publishedAt?.let { formatTime(it) }
                tvRead.setOnClickListener {
                    onClickListener?.invoke(articleData)
                }
            }
        }
    }
}