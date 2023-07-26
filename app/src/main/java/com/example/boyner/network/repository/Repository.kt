package com.example.boyner.network.repository

import com.example.boyner.network.api.ProvideApi
import retrofit2.Retrofit

class Repository {
    suspend fun getTopHeadLines() = ProvideApi.api.getTopHeadlines()
    suspend fun getSource() = ProvideApi.api.getSource()
}   // error katmanı eklenebilir.