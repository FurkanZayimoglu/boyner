package com.example.boyner.model.topHeadlinesModel

import com.google.gson.annotations.SerializedName

data class ArticleSourceData(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)
