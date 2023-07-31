package com.example.boyner.network.api

import com.example.boyner.model.ourcesModel.SourcesData
import com.example.boyner.model.topHeadlinesModel.TopHeadlinesData
import com.example.boyner.util.Constant.Companion.API_KEY
import com.example.boyner.util.Constant.Companion.LANGUAGE_CODE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BoynerNewsApi {

    @GET("v2/top-headlines/sources")
    suspend fun getSource(
        @Query("language") language: String = LANGUAGE_CODE,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<SourcesData>


    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<TopHeadlinesData>

}