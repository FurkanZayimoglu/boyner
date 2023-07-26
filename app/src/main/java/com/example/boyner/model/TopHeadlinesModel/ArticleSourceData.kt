package com.example.boyner.model.TopHeadlinesModel

import com.google.gson.annotations.SerializedName

data class ArticleSourceData(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)
