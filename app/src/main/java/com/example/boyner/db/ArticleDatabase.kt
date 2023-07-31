package com.example.boyner.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boyner.model.topHeadlinesModel.ArticleData


@Database(entities = [ArticleData::class], version = 1)
abstract class ArticleDatabase : RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao
}