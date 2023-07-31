package com.example.boyner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boyner.model.ourcesModel.SourcesSubListData
import com.example.boyner.model.topHeadlinesModel.ArticleData
import com.example.boyner.network.repository.Repository
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _sourcesList = MutableLiveData<MutableList<SourcesSubListData>>()
    val sourcesList: LiveData<MutableList<SourcesSubListData>> get() = _sourcesList

    private val _topHeadlinesArticleList = MutableLiveData<List<ArticleData>>()
    val topHeadlinesArticleList: LiveData<List<ArticleData>> get() = _topHeadlinesArticleList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _errorMessage = MutableLiveData<String>()
     val errorMessage: LiveData<String> get() = _errorMessage

    init {
        getSourceList()
    }

     fun getSourceList() = viewModelScope.launch {
        _loading.value = true
        val response = repository.getSource()
        if (response.isSuccessful) {
            _loading.value = false
            _sourcesList.value = response.body()?.sources?.toMutableList()
        } else {
            _loading.value = false
            _errorMessage.value =  "veriler alınamadı"

        }
    }

    fun getTopHeadLines(source: String) = viewModelScope.launch {
        val response = repository.getTopHeadLines(source)
        _loading.value = true
        if (response.isSuccessful) {
            _loading.value = false
            _topHeadlinesArticleList.value = response.body()?.articles
        } else {
            _loading.value = false
            _errorMessage.value =   "veriler alınamadı"
        }
    }

   /*

   viewmodel de databse işlemleri.




   fun saveArticle(article: ArticleData)= viewModelScope.launch {
        article.isAdded = true
        repository.upsert(article)
    }


    fun deleteSavedArticle(article: ArticleData)= viewModelScope.launch {
        article.isAdded = false
        repository.deleteArticle(article)
    }

    */





}