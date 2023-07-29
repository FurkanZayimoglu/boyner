package com.example.boyner.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boyner.R
import com.example.boyner.databinding.ItemCategoryLayoutBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var sourceList: List<String> = emptyList()
    var onClickListener: ((MutableList<String>) -> Unit)? = null
    private val selectedPositions = mutableListOf<String>()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<String>) {
        sourceList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(sourceList[position])
    }

    override fun getItemCount() = sourceList.size


    inner class CategoryViewHolder(private val binding: ItemCategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(text: String) {
            with(binding) {
                tvCategory.text = text

                if (selectedPositions.contains(text)) {
                    clCategory.setBackgroundResource(R.drawable.category_background_slected)
                    ivIcon.setImageResource(R.mipmap.okey)
                    tvCategory.setTextColor(Color.WHITE)
                } else {
                    clCategory.setBackgroundResource(R.drawable.category_backgorund_not_selected)
                    ivIcon.setImageResource(R.mipmap.ic_launcher)
                    tvCategory.setTextColor(Color.BLACK)
                }

                ivIcon.setOnClickListener {
                    if (selectedPositions.contains(text)) {
                        selectedPositions.remove(text)
                        notifyDataSetChanged()
                    } else {
                        selectedPositions.add(text)
                        notifyDataSetChanged()
                    }
                    onClickListener?.invoke(selectedPositions)
                }
            }
        }
    }

}
