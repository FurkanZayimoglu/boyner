package com.example.boyner.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.boyner.model.topHeadlinesModel.ArticleData

@Dao
interface ArticleDao{

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun upsert(article: ArticleData)

@Delete
suspend fun deleteArticle(article: ArticleData)
}