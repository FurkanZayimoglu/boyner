package com.example.boyner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boyner.network.repository.Repository

class  ViewModelFactory(private val articleRepository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(articleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}