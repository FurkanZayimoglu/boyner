package com.example.boyner.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boyner.R
import com.example.boyner.adapter.CategoryAdapter
import com.example.boyner.adapter.SourcesAdapter
import com.example.boyner.databinding.FragmentSourceNewsBinding
import com.example.boyner.model.SourcesModel.SourcesSubListData
import com.example.boyner.network.repository.Repository
import com.example.boyner.ui.MainActivity
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
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        settingRecycler()
        observeLoading()
        observeData()
        observeError()
        return binding.root
    }

    private fun observeLoading() {
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.pbLoading.visibility = View.VISIBLE
            } else
                binding.pbLoading.visibility = View.GONE
        }
    }

    private fun observeData() {
        viewModel.sourcesList.observe(viewLifecycleOwner) { sourcesSubListData ->
            sourceList = sourcesSubListData
            sourceAdapter.setData(sourceList)
            categoryAdapter.setData(sourceList.map { it.category }.distinct())
        }

    }

    private fun observeError() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            AlertDialog.Builder(context)
                .setTitle("Uyarı")
                .setMessage(errorMessage)
                .setPositiveButton("Tekrar Dene ") { _, _ ->
                    viewModel.getSourceList()
                }
                .setNegativeButton("Vazgeç") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
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

    private fun openTopHeadlinesFragment(id: String, name: String) {
        findNavController().navigate(R.id.action_sourceNewsFragment_to_topHeadlinesSourceFragment,
            Bundle().apply {
                putString("id", id)
                putString("name", name)
            }
        )
    }

    private fun settingRecycler() {
        categoryAdapter = CategoryAdapter()
        binding.rvCategories.adapter = categoryAdapter
        categoryAdapter.onClickListener = { categoriyList ->
            filterCategory(categoriyList)
        }
        binding.rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        sourceAdapter = SourcesAdapter()
        binding.rvSourceNews.adapter = sourceAdapter
        sourceAdapter.onClickListener = { id, name ->
            openTopHeadlinesFragment(id, name)
        }
    }
}