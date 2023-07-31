package com.example.boyner.model.topHeadlinesModel

import com.google.gson.annotations.SerializedName

data class TopHeadlinesData (
    @SerializedName("articles")
    val articles: MutableList<ArticleData>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)