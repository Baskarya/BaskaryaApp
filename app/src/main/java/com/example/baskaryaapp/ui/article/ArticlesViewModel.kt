package com.example.baskaryaapp.ui.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baskaryaapp.data.repo.ArticlesRepository
import com.example.baskaryaapp.data.response.DataItem
import kotlinx.coroutines.launch

class ArticlesViewModel(private val repository: ArticlesRepository) : ViewModel() {
    private val _listArticles = MutableLiveData<List<DataItem>>()
    val listArticles: LiveData<List<DataItem>> = _listArticles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.articles()
                if (!response.error!!) {
                    _listArticles.value = response.data
                } else {
                    Log.e("ArticlesViewModel", "Error load stories: ${response.status}")
                }
//                _listBatik.value = response.data
            } catch (e: Exception) {
                Log.e("ArticlesViewModel", "Error load stories: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}