package com.example.boyner.model.topHeadlinesModel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity(
    tableName = "articles"
)
data class ArticleData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    var isAdded: Boolean = false
):Parcelable
