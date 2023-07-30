package com.example.boyner.network.repository

import com.example.boyner.network.api.ProvideApi
import retrofit2.Retrofit

class Repository {
    suspend fun getTopHeadLines(source: String) = ProvideApi.api.getTopHeadlines(sources = source)
    suspend fun getSource() = ProvideApi.api.getSource()
}   // error katmanı eklenebilir.