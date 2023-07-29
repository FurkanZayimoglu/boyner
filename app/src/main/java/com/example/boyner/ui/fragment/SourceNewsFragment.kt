package com.example.boyner.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.boyner.adapter.CategoryAdapter
import com.example.boyner.adapter.SourcesAdapter
import com.example.boyner.databinding.FragmentSourceNewsBinding
import com.example.boyner.model.SourcesModel.SourcesSubListData
import com.example.boyner.network.repository.Repository
import com.example.boyner.viewmodel.NewsViewModel
import com.example.boyner.viewmodel.ViewModelFactory


class SourceNewsFragment : Fragment() {

    private lateinit var binding: FragmentSourceNewsBinding
    private lateinit var sourceAdapter: SourcesAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private val filteredList = mutableListOf<SourcesSubListData>()
    private var sourceList = mutableListOf<SourcesSubListData>()

    private val repository = Repository()
    private val viewModel: NewsViewModel by viewModels {
        ViewModelFactory(repository)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSourceNewsBinding.inflate(inflater, container, false)

        categoryAdapter = CategoryAdapter()
        binding.rvCategories.adapter = categoryAdapter
        categoryAdapter.onClickListener = { categoriyList ->
            filterCategory(categoriyList)
        }
        binding.rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        sourceAdapter = SourcesAdapter()
        binding.rvSourceNews.adapter = sourceAdapter

        observeData()

        return binding.root
    }

    private fun observeData() {
        viewModel.sourcesList.observe(viewLifecycleOwner) { sourcesSubListData ->
            sourceList = sourcesSubListData
            sourceAdapter.setData(sourceList)
            categoryAdapter.setData(sourceList.map { it.category }.distinct())
        }

    }

    private fun filterCategory(filterCategoryList: MutableList<String>) {
        filteredList.clear()
        for (filterText in filterCategoryList) {
            for (source in sourceList) {
                if (source.category.contains(filterText, ignoreCase = true)) {
                    filteredList.add(source)
                }
            }
        }
        sourceAdapter.apply {
            if (filteredList.isEmpty()) {
                setData(sourceList)
                return
            }
            setData(filteredList)
        }
    }
}