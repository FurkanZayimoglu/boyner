package com.example.boyner.network.repository

import com.example.boyner.network.api.ProvideApi

class Repository {

    suspend fun getTopHeadLines(source: String) = ProvideApi.api.getTopHeadlines(sources = source)
    suspend fun getSource() = ProvideApi.api.getSource()

    /*
    private val db: ArticleDatabase  // constructorda verecektim.
    bu fonksiyonlarıda viewmodel ile kullanacaktım.
    suspend fun upsert(article: ArticleData)= db.getArticleDao().upsert(article)
    suspend fun deleteArticle(article: ArticleData)= db.getArticleDao().deleteArticle(article)
     */
}