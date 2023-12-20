package com.example.baskaryaapp.ui.bookmark.batikpedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baskaryaapp.data.repo.BookmarkBatikRepository

class BookmarkBatikViewModel(private val bookmarkBatikRepository: BookmarkBatikRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "BookmarkBatikViewModel"
    }
}