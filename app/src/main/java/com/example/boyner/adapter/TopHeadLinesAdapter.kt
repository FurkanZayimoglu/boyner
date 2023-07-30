package com.example.boyner.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.boyner.databinding.ItemTopHeadLayoutBinding
import com.example.boyner.model.TopHeadlinesModel.ArticleData
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.TimeZone

class TopHeadLinesAdapter : RecyclerView.Adapter<TopHeadLinesAdapter.TopHeadViewPagerViewHolder>() {

    private var topHeadLinesList: List<ArticleData> = emptyList()

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

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(articleData: ArticleData) {
            with(binding){
                Glide.with(root.context)
                    .load(articleData.urlToImage)
                    .into(ivNewsImage)
                tvNewsTitle.text= articleData.title
                tvTime.text= articleData.publishedAt
            }
        }
    }

}