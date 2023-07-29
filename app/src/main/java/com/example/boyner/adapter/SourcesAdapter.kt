package com.example.boyner.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boyner.databinding.ItemSourceNewsLayoutBinding
import com.example.boyner.model.SourcesModel.SourcesSubListData

class SourcesAdapter() : RecyclerView.Adapter<SourcesAdapter.SourceViewHolder>() {

    private var sourceList: List<SourcesSubListData> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<SourcesSubListData>) {
        sourceList = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val binding =
            ItemSourceNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(sourceList[position])
    }

    override fun getItemCount() = sourceList.size


    inner class SourceViewHolder(private val binding: ItemSourceNewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sourceListData: SourcesSubListData) {
            with(binding) {
                tvNewsTitle.text = sourceListData.name
                tvNewsDescription.text = sourceListData.description
            }
        }
    }
}


