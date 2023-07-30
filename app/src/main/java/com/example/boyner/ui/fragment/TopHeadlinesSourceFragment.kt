package com.example.boyner.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.boyner.adapter.TopHeadLinesAdapter
import com.example.boyner.databinding.FragmentTopHeadlinesSourceBinding
import com.example.boyner.network.repository.Repository
import com.example.boyner.ui.MainActivity
import com.example.boyner.viewmodel.NewsViewModel
import com.example.boyner.viewmodel.ViewModelFactory

class TopHeadlinesSourceFragment : Fragment() {
    private val repository = Repository()
    private val viewModel: NewsViewModel by viewModels {
        ViewModelFactory(repository)
    }
    private var binding: FragmentTopHeadlinesSourceBinding? = null
    private val args: TopHeadlinesSourceFragmentArgs by navArgs()
    private lateinit var topHeadLinesAdapter: TopHeadLinesAdapter
    private lateinit var topHeadLinesAdapterToHorizontal: TopHeadLinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopHeadlinesSourceBinding.inflate(inflater, container, false)
        (activity as MainActivity).changeToolbarTitle(args.name)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setRecyclerSetting()
        observeLoading()
        observeData()
        observeError()
        return binding!!.root
    }

    private fun observeLoading() {
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding?.apply {
                    pbLoading.visibility = View.VISIBLE
                    topHeadlinesSourceFragment.visibility = View.GONE
                    indicator.visibility = View.GONE
                }
            } else {
                binding?.apply {
                    pbLoading.visibility = View.GONE
                    topHeadlinesSourceFragment.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observeData() {
        viewModel.getTopHeadLines(args.id)
        viewModel.topHeadlinesArticleList.observe(viewLifecycleOwner) {
            topHeadLinesAdapterToHorizontal.setData(it.subList(0, 3))
            topHeadLinesAdapter.setData(it.subList(3, it.size))
            binding?.indicator?.visibility = View.VISIBLE
        }
    }

    private fun observeError(){
        viewModel.errorMessage.observe(viewLifecycleOwner){ errorMessage ->
            AlertDialog.Builder(context)
                .setTitle("Uyarı")
                .setMessage(errorMessage)
                .setPositiveButton("Tekrar Dene ") { _, _ ->
                    viewModel.getTopHeadLines(args.id)
                }
                .setNegativeButton("Vazgeç") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
        }
    }

    private fun setRecyclerSetting() {
        topHeadLinesAdapter = TopHeadLinesAdapter()
        topHeadLinesAdapterToHorizontal = TopHeadLinesAdapter()
        binding!!.apply {
            rvSourceNews.adapter = topHeadLinesAdapter
            rvHorizontal.adapter = topHeadLinesAdapterToHorizontal
            rvHorizontal.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val pagerSnapHelper = PagerSnapHelper()
            pagerSnapHelper.attachToRecyclerView(rvHorizontal)
            indicator.attachToRecyclerView(rvHorizontal, pagerSnapHelper)
            indicator.createIndicators(3, 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        (activity as MainActivity).getActivityTitle()

    }
}