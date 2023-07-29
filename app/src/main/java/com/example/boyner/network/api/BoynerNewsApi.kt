package com.example.boyner.network.api

import com.example.boyner.model.SourcesModel.SourcesData
import com.example.boyner.model.TopHeadlinesModel.TopHeadlinesData
import com.example.boyner.util.Constant.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BoynerNewsApi {

    @GET("v2/top-headlines/sources")
    suspend fun getSource(
        @Query("language") language: String? = "en",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<SourcesData>


    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<TopHeadlinesData>      // source un idsi eklencek buraya 
}