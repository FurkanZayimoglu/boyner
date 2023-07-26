package com.example.boyner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boyner.model.SourcesModel.SourcesData
import com.example.boyner.model.SourcesModel.SourcesSubListData
import com.example.boyner.model.TopHeadlinesModel.ArticleData
import com.example.boyner.model.TopHeadlinesModel.TopHeadlinesData
import com.example.boyner.network.Resource
import com.example.boyner.network.repository.Repository
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _sourcesList = MutableLiveData<List<SourcesSubListData>>()
    val sourcesList: LiveData<List<SourcesSubListData>> get() = _sourcesList

    private val _topHeadlinesArticleList = MutableLiveData<List<ArticleData>>()
    val topHeadlinesArticleList: LiveData<List<ArticleData>> get() = _topHeadlinesArticleList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    init {
        getSourceList()
    }

    private fun getSourceList() = viewModelScope.launch {
        val response = repository.getSource()
        _loading.value = true
        if (response.isSuccessful) {
            _loading.value = false
            _sourcesList.value = response.body()?.sources
        } else {
            _loading.value = false
            Resource.Error<SourcesData>("Error")
        }
    }

    fun getTopHeadLines() = viewModelScope.launch {
        val response = repository.getTopHeadLines()
        _loading.value = true
        if (response.isSuccessful) {
            _loading.value = false
            _topHeadlinesArticleList.value = response.body()?.articles
        } else {
            _loading.value = false
            Resource.Error<TopHeadlinesData>("Error")
        }
    }

}