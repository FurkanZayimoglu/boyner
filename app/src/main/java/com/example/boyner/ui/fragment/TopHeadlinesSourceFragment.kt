package com.example.boyner.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.boyner.adapter.TopHeadLinesAdapter
import com.example.boyner.databinding.FragmentTopHeadlinesSourceBinding
import com.example.boyner.ui.MainActivity
import com.example.boyner.viewmodel.NewsViewModel
import kotlin.math.max
class TopHeadlinesSourceFragment : Fragment() {
    private var binding: FragmentTopHeadlinesSourceBinding? = null
    private val args: TopHeadlinesSourceFragmentArgs by navArgs()
    private lateinit var topHeadLinesAdapter: TopHeadLinesAdapter
    private lateinit var topHeadLinesAdapterToHorizontal: TopHeadLinesAdapter
    private lateinit var viewModel: NewsViewModel
    private var currentPosition = 0
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val delayMillis = 5000L
    private val sliderSize : Int = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopHeadlinesSourceBinding.inflate(inflater, container, false)
        (activity as MainActivity).changeToolbarTitle(args.name)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel =  (activity as MainActivity).viewModel
        handler = HandlerCompat.createAsync(Looper.getMainLooper())
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
            topHeadLinesAdapterToHorizontal.setData(it.subList(0,sliderSize))
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
        topHeadLinesAdapter.onClickListener = { articleData ->
           // savedDataOperations(articleData)
        }
        topHeadLinesAdapterToHorizontal = TopHeadLinesAdapter()
        topHeadLinesAdapterToHorizontal.onClickListener = {articleData->
            // savedDataOperations(articleData)
        }
        binding!!.apply {
            rvSourceNews.adapter = topHeadLinesAdapter
            rvHorizontal.adapter = topHeadLinesAdapterToHorizontal
            rvHorizontal.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val pagerSnapHelper = PagerSnapHelper()
            pagerSnapHelper.attachToRecyclerView(rvHorizontal)
            indicator.attachToRecyclerView(rvHorizontal, pagerSnapHelper)
            indicator.createIndicators(sliderSize, 0)
            delayedRecycler(rvHorizontal, sliderSize)  // slider için otomatik 5 saniyede bir  değişim verildi.
        }
        }

    private fun delayedRecycler(recyclerView: RecyclerView, size: Int) {
        runnable = Runnable {
            currentPosition = max(0, (currentPosition + 1) % size)
            recyclerView.smoothScrollToPosition(currentPosition)
            handler.postDelayed(runnable, delayMillis) // 5000 ms (5 saniye) gecikme
        }
        handler.postDelayed(runnable,delayMillis)
    }

    /*
        private fun savedDataOperations(articleData: ArticleData) {
              if (articleData.isAdded){
                viewModel.deleteSavedArticle(articleData)
            }else {
                viewModel.saveArticle(articleData)
            }
            recyler içinde tıklana item bu fonk aracılığı ile viewmodele  ulaşıp database ekleme ve cıkarma yapacaktı
            eklenip eklenmeme durumuna gore de kontrol edecekti ve ona göre yapacaktı burada daha farklı  kontroller tutabilriz listenin pozisyonuna göre ama
            yapmak istediğim mantığı göstermek istedim.
    }
  */

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        (activity as MainActivity).getActivityTitle()
        handler.removeCallbacks(runnable)
    }
}